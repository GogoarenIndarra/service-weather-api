package com.gogoaren.indarra.serviceweatherapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogoaren.indarra.serviceweatherapi.ServiceWeatherApiApplication;
import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import com.gogoaren.indarra.serviceweatherapi.fetch.openweather.OpenWeatherFetcher;
import com.gogoaren.indarra.serviceweatherapi.fetch.openweather.OpenWeatherResponseConverter;
import com.gogoaren.indarra.serviceweatherapi.kafka.KafkaMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ServiceWeatherApiApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class RestControllerTests {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WeatherUtil weatherUtil;
    @MockBean
    protected Clock clock;
    @MockBean
    protected OpenWeatherFetcher openWeatherFetcher;
    @MockBean
    protected OpenWeatherResponseConverter converter;
    @MockBean
    protected KafkaMessageSender kafkaMessageSender;
    @MockBean
    protected Map<String, Weather> cacheWeatherMap;
}
