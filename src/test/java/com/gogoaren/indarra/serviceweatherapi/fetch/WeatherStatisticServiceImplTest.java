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

public class WeatherStatisticServiceImplTest {
    /* this not work: why? */
//    @Mock
//    private WeatherEntityService weatherEntityService;
//    @InjectMocks
//    private final WeatherStatisticServiceImpl weatherStatisticService =
//            new WeatherStatisticServiceImpl(weatherEntityService);

    private WeatherEntityService weatherEntityService;
    private WeatherStatisticServiceImpl weatherStatisticService;

    @BeforeEach
    public void setup() {
        weatherEntityService = mock(WeatherEntityService.class);
        weatherStatisticService = new WeatherStatisticServiceImpl(weatherEntityService);
    }

    @Test
    public void shouldReturnOneSizeMapWhenWarmestCityProvided() {
        //given
        final String city = "Rome";
        final BigDecimal expectedTemperature = new BigDecimal("33");

        when(weatherEntityService.findTopWarmestCity(1)).thenReturn(List.of(TestUtils.createWeatherEntity(city, expectedTemperature)));

        //when
        var warmestCityMap = weatherStatisticService.findWarmestCity();

        //then
        Assertions.assertThat(warmestCityMap.containsKey("Rome"));
        Assertions.assertThat(warmestCityMap.containsValue(expectedTemperature));
        Assertions.assertThat(warmestCityMap.size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnTenSizeMapWhenTenTopWarmestCitiesProvided() {
        //given
        final String city = "Rome";
        var listOfCities = IntStream.range(1, 11)
                        .mapToObj(i -> TestUtils.createWeatherEntity(city + i, new BigDecimal(22 + i)))
                        .collect(Collectors.toList());

        when(weatherEntityService.findTopWarmestCity(10)).thenReturn(listOfCities);

        //when
        var warmestCityMap = weatherStatisticService.findTopTenWarmestCity();

        //then
        Assertions.assertThat(warmestCityMap.containsKey("Rome1"));
        Assertions.assertThat(warmestCityMap.containsValue(new BigDecimal(23)));
        Assertions.assertThat(warmestCityMap.size()).isEqualTo(10);
    }
}