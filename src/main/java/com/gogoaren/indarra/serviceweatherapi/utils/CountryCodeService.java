package com.gogoaren.indarra.serviceweatherapi.utils;

import java.util.Locale;

public class CountryCodeService {

    public static String iso2CountryCodeToCountryName(final String iso2CountryCode) {

        Locale locale = new Locale("", iso2CountryCode);

        return locale.getDisplayCountry();
    }
}
