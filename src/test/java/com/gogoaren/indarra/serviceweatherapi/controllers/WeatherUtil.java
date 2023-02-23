package com.gogoaren.indarra.serviceweatherapi.controllers;

import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
public class WeatherUtil {

    public Weather mockWeather(final Map<String, Object> mockedData) {

        return Weather.builder()
                .city((String) Optional.ofNullable(mockedData.get("city"))
                        .orElse("Warsaw"))
                .temperature((BigDecimal) Optional.ofNullable(mockedData.get("temperature"))
                        .orElse(BigDecimal.valueOf(23.23)))
                .humidity((Double) Optional.ofNullable(mockedData.get("humidity"))
                        .orElse(Double.valueOf("78.12")))
                .wind((Double) Optional.ofNullable(mockedData.get("wind"))
                        .orElse(Double.valueOf("14.12")))
                .country((String) Optional.ofNullable(mockedData.get("country"))
                        .orElse("Poland"))
                .countryCode((String) Optional.ofNullable(mockedData.get("countryCode"))
                        .orElse("PL"))
                .build();
    }
}
