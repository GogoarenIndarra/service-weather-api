package com.gogoaren.indarra.serviceweatherapi.controllers;

import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import com.gogoaren.indarra.serviceweatherapi.fetch.openweather.OpenWeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
class WeatherControllerTest extends RestControllerTests {

    final static Instant instantDate = Instant.parse("2018-11-30T18:35:24.00Z");
    final static String URL = "/api/weather";

    @Test
    void getWeatherByCity() throws Exception {
        //given:
        final var cityName = "Warsaw";
        final Weather weather = weatherUtil.mockWeather(new HashMap<>());
        Mockito.when(cacheWeatherMap.containsKey(cityName)).thenReturn(false);
        Mockito.when(openWeatherFetcher.fetchWeatherByCityName(cityName)).thenReturn(new OpenWeatherResponse());
        Mockito.when(converter.convert(any())).thenReturn(weather);

        //when:
        ResultActions result = mvc.perform(delete(URL + "/" + cityName));

        //then:
//        result
//                .andExpect(status().isOk())
//                .andExpect(content().json(String.format("""
//                        {
//                            "status":404,
//                            "message":"User not found",
//                            "time":"2018-11-30T18:35:24Z",
//                            "path": "%s/%S"
//                        }
//                        """, URL, cityName)));

        log.info(String.valueOf(result));


//        Assertions.assertEquals();
    }
}