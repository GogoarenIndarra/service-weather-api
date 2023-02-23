package com.gogoaren.indarra.serviceweatherapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomCitiesGenerator {

    private static Random rand;

    static {
        try {
            rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getRandomCity() {
        final List<String> cityNames = Arrays.asList(
                "San Sebastian", "Warsaw", "Shanghai", "Alicante", "Albuquerque", "Moscow",
                "Jijona", "Novomoskovsk", "Gebze", "Briançon", "Jeddah", "Santiago de Cali",
                "Port Montt", "Cape Town", "Bilbao", "Rostov", "Cairo", "Lagos", "Gyöngyös",
                "Provincia di Pescara", "Euskirchen");

        return cityNames.get(rand.nextInt(cityNames.size()));
    }

    public static List<String> getCityList() {
        return Arrays.asList(
                "San Sebastian", "Warsaw", "Shanghai", "Alicante", "Albuquerque", "Moscow",
                "Jijona", "Novomoskovsk", "Gebze", "Briançon", "Jeddah", "Santiago de Cali",
                "Port Montt", "Cape Town", "Bilbao", "Rostov", "Cairo", "Lagos", "Gyöngyös",
                "Provincia di Pescara", "Euskirchen");
    }
}
