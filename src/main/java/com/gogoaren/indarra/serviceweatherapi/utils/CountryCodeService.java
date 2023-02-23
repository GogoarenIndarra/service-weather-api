package com.gogoaren.indarra.serviceweatherapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CountryCodeService {

    public static String iso2CountryCodeToCountryName(final String iso2CountryCode) {
        final Locale locale = new Locale("", iso2CountryCode);
        return locale.getDisplayCountry();
    }
}
