package com.gogoaren.indarra.serviceweatherapi.fetch.openweather;

import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import com.gogoaren.indarra.serviceweatherapi.utils.CountryCodeService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OpenWeatherResponseConverter {

    public Weather convert(final OpenWeatherResponse openWeatherResponse) {

        return Weather.builder()
                .city(openWeatherResponse.getName())
                .temperature(convertTemperature(openWeatherResponse.getMain().getTemp()))
                .humidity(openWeatherResponse.getMain().getHumidity())
                .wind(openWeatherResponse.getWind().getSpeed())
                .countryCode(openWeatherResponse.getSys().getCountry())
                .country(CountryCodeService.iso2CountryCodeToCountryName(openWeatherResponse.getSys().getCountry()))
                .build();
    }

    private BigDecimal convertTemperature(final double kelvinTemp) {
        final BigDecimal a = BigDecimal.valueOf(kelvinTemp);
        final BigDecimal b = BigDecimal.valueOf(273.15);

        return a.subtract(b).setScale(1, RoundingMode.CEILING);
    }
}
