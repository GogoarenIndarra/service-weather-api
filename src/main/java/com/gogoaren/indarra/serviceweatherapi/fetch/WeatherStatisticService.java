package com.gogoaren.indarra.serviceweatherapi.fetch;

import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WeatherStatisticService {

    Map<String, BigDecimal> findWarmestCity();

    Map<String, BigDecimal> findTopTenWarmestCity();

    Map<String, List<String>> findCitiesFromCountry(String countryCode);

    Map<String, BigDecimal> findMaxTemperatureForAllCities();

    Set<String> findCitiesWithTempTenDegreeOrHigher();

    Map<String, List<WeatherEntity>> findCitiesFromAllCountries();
}