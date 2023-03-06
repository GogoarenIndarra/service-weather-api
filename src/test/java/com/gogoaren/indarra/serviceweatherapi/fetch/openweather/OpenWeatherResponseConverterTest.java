package com.gogoaren.indarra.serviceweatherapi.fetch.openweather;

import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.gogoaren.indarra.serviceweatherapi.TestUtils.createOpenWeatherResponse;

class OpenWeatherResponseConverterTest {

    private OpenWeatherResponseConverter openWeatherResponseConverter = new OpenWeatherResponseConverter();

    @BeforeEach
    void setup() {
        openWeatherResponseConverter = new OpenWeatherResponseConverter();
    }

    @Test
    void shouldConvertOpenWeatherResponse() throws Exception {

        final Weather expectedWeather = Weather.builder()
                .country("Stany Zjednoczone")
                .wind(1.5)
                .city("Mountain View")
                .humidity(100)
                .temperature(BigDecimal.valueOf((9.40)))
                .countryCode("US")
                .build();

        final Weather actualWeather = openWeatherResponseConverter.convert(createOpenWeatherResponse());

        Assertions.assertEquals(expectedWeather, actualWeather);
    }
}