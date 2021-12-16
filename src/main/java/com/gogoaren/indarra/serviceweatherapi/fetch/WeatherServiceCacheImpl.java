package com.gogoaren.indarra.serviceweatherapi.fetch;

import com.gogoaren.indarra.serviceweatherapi.fetch.openweather.OpenWeatherFetcher;
import com.gogoaren.indarra.serviceweatherapi.fetch.openweather.OpenWeatherResponse;
import com.gogoaren.indarra.serviceweatherapi.fetch.openweather.OpenWeatherResponseConverter;
import com.gogoaren.indarra.serviceweatherapi.kafka.KafkaMessageSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@AllArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "whether.service.implementation.type", havingValue = "cache")
public class WeatherServiceCacheImpl implements WeatherService {

    private final OpenWeatherFetcher openWeatherFetcher;
    private final OpenWeatherResponseConverter converter;
    private final KafkaMessageSender kafkaMessageSender;

    private final Map<String, Weather> cacheWeatherMap = new ConcurrentHashMap<>();

    @Override
    public Weather getWeatherByCity(String city) {

        if (cacheWeatherMap.containsKey(city)) {
            log.info("weather from cache: " + city);
            return cacheWeatherMap.get(city);
        }
        return getWeatherFromOpenWeather(city);

    }

    private Weather getWeatherFromOpenWeather(String city) {
        log.info("fetching weather for: " + city);
        OpenWeatherResponse openWeatherResponse = openWeatherFetcher.fetchWeatherByCityName(city);
        log.info("open weather response: " + openWeatherResponse);
        Weather weather = converter.convert(openWeatherResponse);
        cacheWeatherMap.put(city, weather);
        kafkaMessageSender.sendMessage(weather);
        return weather;
    }

    @Scheduled(cron = "${weather.schedule.cron.clean.cache}")
    private void clearCache() {
        cacheWeatherMap.clear();
    }

}
