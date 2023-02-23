package com.gogoaren.indarra.serviceweatherapi.controllers;

import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntity;
import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntityService;
import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import com.gogoaren.indarra.serviceweatherapi.fetch.WeatherService;
import com.gogoaren.indarra.serviceweatherapi.fetch.WeatherStatisticService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.gogoaren.indarra.serviceweatherapi.utils.CustomStringConverter.stringConverterCity;

@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, path = "/api/weather")
@RestController
@AllArgsConstructor
@Slf4j
public class WeatherController {

    WeatherService weatherService;
    WeatherStatisticService weatherStatisticService;
    WeatherEntityService weatherEntityService;

    @GetMapping(value = "/{city}")
    public ResponseEntity<Weather> getWeatherByCity(@PathVariable final String city) {
        return new ResponseEntity<>(weatherService.getWeatherByCity(stringConverterCity(city)), HttpStatus.OK);
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
    public void saveEntity(@RequestBody final Weather weather) {
        weatherEntityService.saveEntity(new WeatherEntity(weather));
    }

    @GetMapping(value = "/temperatureOverTenDegree")
    public Set<String> getStatisticForTemperature() {
        return weatherStatisticService.findCitiesWithTempTenDegreeOrHigher();
    }

    @GetMapping(value = "/statistic/{countryCode}")
    public Map<String, List<String>> getStatisticForCountry(@PathVariable final String countryCode) {
        return weatherStatisticService.findCitiesFromCountry(countryCode);
    }

    @GetMapping(value = "/getCities")
    public Map<String, List<WeatherEntity>> getCitiesFromCountry() {
        return weatherStatisticService.findCitiesFromAllCountries();
    }


}



