package com.gogoaren.indarra.serviceweatherapi.fetch.openweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogoaren.indarra.serviceweatherapi.TestUtils;
import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenWeatherResponseConverterTest {

    OpenWeatherResponseConverter openWeatherResponseConverter = new OpenWeatherResponseConverter();

    @Test
    public void shouldConvertOpenWeatherResponse() throws Exception {

        Weather expectedWeather = Weather.builder()
                .country("Stany Zjednoczone")
                .wind(1.5)
                .city("Mountain View")
                .humidity(100)
                .temperature(BigDecimal.valueOf(9.41))
                .countryCode("US")
                .build();

        assertEquals(expectedWeather, openWeatherResponseConverter.convert(createOpenWeatherResponse()));
    }

    private OpenWeatherResponse createOpenWeatherResponse() throws Exception {
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