package com.gogoaren.indarra.serviceweatherapi.controllers;


import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntity;
import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntityService;
import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import com.gogoaren.indarra.serviceweatherapi.fetch.WeatherService;
import com.gogoaren.indarra.serviceweatherapi.fetch.WeatherStatisticService;
import com.gogoaren.indarra.serviceweatherapi.utils.CustomStringConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, path = "/api/weather")
@RestController
@AllArgsConstructor
@Slf4j
public class WeatherController {

    WeatherService weatherService;
    WeatherStatisticService weatherStatisticService;
    WeatherEntityService weatherEntityService;

    @GetMapping(value = "/{city}")
    public Weather getWeatherByCity(@PathVariable String city) {
        return weatherService.getWeatherByCity(CustomStringConverter.stringConverterCity(city));
    }

    @GetMapping(value = "/topCity")
    public Map<String, BigDecimal> findWarmestCity() {
        return weatherStatisticService.findWarmestCity();
    }

    @GetMapping(value = "/topTen")
    public Map<String, BigDecimal> findTopTenWarmestCity() {
        return weatherStatisticService.findTopTenWarmestCity();
    }

    @PostMapping(value = "/saveCity")
    public void saveEntity(@RequestBody Weather weather) {
        weatherEntityService.saveEntity(new WeatherEntity(weather));
    }

    @GetMapping(value = "/temperatureOverTenDegree")
    public Set<String> getStatisticForTemperature() {
        return weatherStatisticService.findCitiesWithTempTenDegreeOrHigher();
    }

    @GetMapping(value = "/statistic/{countryCode}")
    public Map<String, List<String>> getStatisticForCountry(@PathVariable String countryCode) {
        return weatherStatisticService.findCitiesFromCountry(countryCode);
    }

    @GetMapping(value = "/getCities")
    public Map<String, List<WeatherEntity>> getCitiesFromCountry() {
        return weatherStatisticService.findCitiesFromAllCountries();
    }


}



