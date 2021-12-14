package com.gogoaren.indarra.serviceweatherapi.utils;

import org.apache.commons.lang3.StringUtils;

public class CustomStringConverter {

    public static String stringConverterCity(String cityName) {
        return StringUtils.capitalize(StringUtils.lowerCase(cityName));

    }
}
