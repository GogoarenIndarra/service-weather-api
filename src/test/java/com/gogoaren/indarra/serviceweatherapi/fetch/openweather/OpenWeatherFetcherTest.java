package com.gogoaren.indarra.serviceweatherapi.fetch.openweather;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class OpenWeatherFetcherTest {

    private final Logger logger = LoggerFactory.getLogger(OpenWeatherFetcher.class);

    @Autowired
    private OpenWeatherFetcher openWeatherFetcher;

    @Test
    public void getWeatherByCityName() {
        OpenWeatherResponse openWeatherResponse = openWeatherFetcher.fetchWeatherByCityName("London");
        logger.info(openWeatherResponse.toString());
        assertTrue(true);
    }

    @Test
    public void getWeatherByCityLocation() {
        OpenWeatherResponse openWeatherResponse = openWeatherFetcher.fetchWeatherByCityLocation(23, 56);
        logger.info(openWeatherResponse.toString());
        assertTrue(true);
    }

}