package com.gogoaren.indarra.serviceweatherapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomStringConverter {

    public static String stringConverterCity(final String cityName) {
        return StringUtils.capitalize(StringUtils.lowerCase(cityName));
    }
}
