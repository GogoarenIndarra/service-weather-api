package com.gogoaren.indarra.serviceweatherapi.fetch;


import com.gogoaren.indarra.serviceweatherapi.TestUtils;
import com.gogoaren.indarra.serviceweatherapi.data.weather.WeatherEntityService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WeatherStatisticServiceImplTest {

    private WeatherEntityService weatherEntityService;
    private WeatherStatisticServiceImpl weatherStatisticService;

    @BeforeEach
    void setup() {
        weatherEntityService = mock(WeatherEntityService.class);
        weatherStatisticService = new WeatherStatisticServiceImpl(weatherEntityService);
    }

    @Test
    void shouldReturnOneSizeMapWhenWarmestCityProvided() {
        //given
        final String city = "Rome";
        final BigDecimal expectedTemperature = new BigDecimal("33");

        when(weatherEntityService.findTopWarmestCity(1)).thenReturn(List.of(TestUtils.createWeatherEntity(city, expectedTemperature)));

        //when
        final var warmestCityMap = weatherStatisticService.findWarmestCity();

        //then
        Assertions.assertThat(warmestCityMap.containsKey(city)).isEqualTo(true);
        Assertions.assertThat(warmestCityMap.containsValue(expectedTemperature)).isEqualTo(true);
        Assertions.assertThat(warmestCityMap.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnTenSizeMapWhenTenTopWarmestCitiesProvided() {
        //given
        final String city = "Rome";
        final var listOfCities = IntStream.range(1, 11)
                .mapToObj(i -> TestUtils.createWeatherEntity(city + i, new BigDecimal(22 + i)))
                .collect(Collectors.toList());

        when(weatherEntityService.findTopWarmestCity(10)).thenReturn(listOfCities);

        //when
        final var warmestCityMap = weatherStatisticService.findTopTenWarmestCity();

        //then
        Assertions.assertThat(warmestCityMap.containsKey("Rome1")).isEqualTo(true);
        Assertions.assertThat(warmestCityMap.containsValue(new BigDecimal(23))).isEqualTo(true);
        Assertions.assertThat(warmestCityMap.size()).isEqualTo(10);
    }
}