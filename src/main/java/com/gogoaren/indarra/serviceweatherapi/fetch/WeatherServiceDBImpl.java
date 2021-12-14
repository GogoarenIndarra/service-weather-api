package com.gogoaren.indarra.serviceweatherapi.fetch;

import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntity;
import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntityService;
import com.gogoaren.indarra.serviceweatherapi.fetch.openweather.OpenWeatherFetcher;
import com.gogoaren.indarra.serviceweatherapi.fetch.openweather.OpenWeatherResponse;
import com.gogoaren.indarra.serviceweatherapi.fetch.openweather.OpenWeatherResponseConverter;
import com.gogoaren.indarra.serviceweatherapi.kafka.KafkaMessageSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "whether.service.implementation.type", havingValue = "db.migration")
public class WeatherServiceDBImpl implements WeatherService {

    private final OpenWeatherFetcher openWeatherFetcher;
    private final OpenWeatherResponseConverter converter;
    private final WeatherEntityService weatherEntityService;
    private final KafkaMessageSender kafkaMessageSender;
    public static Long timeForWeatherExpire;

    @Autowired
    public void setTimeForWeatherExpire(@Value("${weather.expiration.time.ms}") Long value) {
        timeForWeatherExpire = value;
    }

    @Override
    public Weather getWeatherByCity(String city) {

        Optional<WeatherEntity> optionalWeatherEntity =
                weatherEntityService.findLatestStoredTemperature(city);
        if (optionalWeatherEntity.isPresent() && isWeatherActual(optionalWeatherEntity.get())) {

            log.info("weather from db: " + optionalWeatherEntity);
            return getWeather(optionalWeatherEntity);
        }
        return geWeatherDBimpl(city);
    }

    private Weather geWeatherDBimpl(String city) {
        log.info("fetching weather for: " + city);
        OpenWeatherResponse openWeatherResponse = openWeatherFetcher.fetchWeatherByCityName(city);
        log.info("open weather response: " + openWeatherResponse);
        Weather weather = converter.convert(openWeatherResponse);
        WeatherEntity weatherEntity = getWeatherEntity(weather);
        log.info("save weather entity: " + weatherEntity.toString());
        weatherEntityService.saveEntity(weatherEntity);
        kafkaMessageSender.sendMessage(weather);
        return weather;
    }


    public WeatherEntity getWeatherEntity(Weather weather) {
        return WeatherEntity.builder()
                .uuid(UUID.randomUUID())
                .created(Instant.now())
                .city(weather.getCity())
                .humidity(weather.getHumidity())
                .temperature(weather.getTemperature())
                .wind(weather.getWind())
                .country(weather.getCountry())
                .countryCode(weather.getCountryCode())
                .build();
    }


    public Weather getWeather(Optional<WeatherEntity> optionalWeatherEntity) {
        WeatherEntity weatherEntity = optionalWeatherEntity.get();
        return Weather.builder()
                .humidity(weatherEntity.getHumidity())
                .city(weatherEntity.getCity())
                .temperature(weatherEntity.getTemperature())
                .wind(weatherEntity.getWind())
                .country(weatherEntity.getCountry())
                .countryCode(weatherEntity.getCountryCode())
                .build();
    }


    public boolean isWeatherActual(WeatherEntity weatherEntity) {

        long time = weatherEntity.getCreated().toEpochMilli();
        long ct = Instant.now().toEpochMilli();
        long df = Math.abs(ct - time);

        return df < timeForWeatherExpire;
    }
}
