package com.gogoaren.indarra.serviceweatherapi.fetch;

import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntity;
import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class WeatherStatisticServiceImpl implements WeatherStatisticService {

    private final WeatherEntityService weatherEntityService;

    @Override
    public Map<String, BigDecimal> findWarmestCity() {

        return weatherEntityService.findTopWarmestCity(1)
                .stream()
                .collect(Collectors.toMap(WeatherEntity::getCity, WeatherEntity::getTemperature));
    }

    @Override
    public Map<String, BigDecimal> findTopTenWarmestCity() {

        return weatherEntityService.findTopWarmestCity(10).stream()
                .collect(Collectors.toMap(WeatherEntity::getCity, WeatherEntity::getTemperature));
    }

    @Override
    public Set<String> findCitiesWithTempTenDegreeOrHigher() {

        return weatherEntityService.selectAllForStreamPractice()
                .stream()
                .filter(w -> w.getTemperature().compareTo(BigDecimal.valueOf(10)) >= 0)
                .map(WeatherEntity::getCity)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, List<String>> findCitiesFromCountry(final String countryCode) {

        final Set<String> distinctCity = weatherEntityService.selectAllForStreamPractice()
                .stream()
                .filter(w -> countryCode.equals(w.getCountryCode()))
                .map(WeatherEntity::getCity)
                .collect(Collectors.toSet());
        final Map<String, List<String>> result = new HashMap<>();
        result.put(countryCode, List.copyOf(distinctCity));

        return result;
    }

    public Map<String, List<WeatherEntity>> findCitiesFromAllCountries() {

        return weatherEntityService.selectAllForStreamPractice()
                .stream()
                .collect(Collectors.groupingBy(WeatherEntity::getCountry));
    }

    @Override
    public Map<String, BigDecimal> findMaxTemperatureForAllCities() {
        return null;
    }
}
