package com.gogoaren.indarra.serviceweatherapi.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomCitiesGenerator {

    public static String getRandomCity() {
        List<String> cityNames = Arrays.asList(
                "San Sebastian", "Warsaw", "Shanghai", "Alicante", "Albuquerque", "Moscow",
                "Jijona", "Novomoskovsk", "Gebze", "Briançon", "Jeddah", "Santiago de Cali",
                "Port Montt", "Cape Town", "Bilbao", "Rostov", "Cairo", "Lagos", "Gyöngyös",
                "Provincia di Pescara", "Euskirchen");

        Random rand = new Random();
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
