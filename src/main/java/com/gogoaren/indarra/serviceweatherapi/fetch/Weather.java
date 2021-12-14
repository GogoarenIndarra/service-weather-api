package com.gogoaren.indarra.serviceweatherapi.fetch;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Weather {

    private String city;
    private BigDecimal temperature;
    private double humidity;
    private double wind;
    private String country;
    private String countryCode;
}
