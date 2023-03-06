package com.gogoaren.indarra.serviceweatherapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntity;
import com.gogoaren.indarra.serviceweatherapi.fetch.openweather.OpenWeatherResponse;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

public class TestUtils {

    public static WeatherEntity createWeatherEntity(final String cityName, BigDecimal temperature) {
        return WeatherEntity.builder()
                .uuid(UUID.randomUUID())
                .created(Instant.now())
                .city(cityName)
                .humidity(0.0)
                .temperature(temperature)
                .wind(0.0)
                .build();
    }

    public static OpenWeatherResponse createOpenWeatherResponse() throws Exception {
        ClassLoader classLoader = TestUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("inputForTest_weatherResponseConverter.json");

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + "inputForTest_weatherResponseConverter.json");
        } else {
            String jsonResponse = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonResponse, OpenWeatherResponse.class);
        }
    }


}
