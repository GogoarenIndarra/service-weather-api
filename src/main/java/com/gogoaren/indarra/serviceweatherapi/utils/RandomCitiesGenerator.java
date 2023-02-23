package com.gogoaren.indarra.serviceweatherapi.utils;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomCitiesGenerator {
    private static final Faker faker = new Faker();

    public static String getRandomCity() {
        return faker.country().capital();
    }
}
