package com.gogoaren.indarra.serviceweatherapi;


import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntity;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static String readFileAsString(String file) throws Exception {
        Assert.notNull(file, "file does not exist");
        return new String(Files.readAllBytes(Paths.get(file)));
    }


}
