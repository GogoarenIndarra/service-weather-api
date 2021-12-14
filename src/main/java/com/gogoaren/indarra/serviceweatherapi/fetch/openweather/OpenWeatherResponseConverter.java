package com.gogoaren.indarra.serviceweatherapi.fetch.openweather;

import com.gogoaren.indarra.serviceweatherapi.fetch.Weather;
import com.gogoaren.indarra.serviceweatherapi.utils.CountryCodeService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OpenWeatherResponseConverter {

    public Weather convert(OpenWeatherResponse openWeatherResponse) {
        return Weather.builder()
                .city(openWeatherResponse.getName())
                .temperature(convertTemperature(openWeatherResponse.getMain().getTemp()))
                .humidity(openWeatherResponse.getMain().getHumidity())
                .wind(openWeatherResponse.getWind().getSpeed())
                .countryCode(openWeatherResponse.getSys().getCountry())
                .country(CountryCodeService.iso2CountryCodeToCountryName(openWeatherResponse.getSys().getCountry()))
                .build();
    }

    private BigDecimal convertTemperature(double kelvineTemp) {

        BigDecimal a = new BigDecimal(kelvineTemp);
        BigDecimal b = new BigDecimal(273.15);
        return a.subtract(b).setScale(2, RoundingMode.CEILING);
    }

}
