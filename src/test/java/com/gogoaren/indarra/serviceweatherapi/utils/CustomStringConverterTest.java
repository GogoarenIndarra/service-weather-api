package com.gogoaren.indarra.serviceweatherapi.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
class CustomStringConverterTest {

    @ParameterizedTest
    @MethodSource("input")
    void stringConverterCity_shouldReturnTrue(String inputStr, String outputStr) {
        assertEquals(outputStr, CustomStringConverter.stringConverterCity(inputStr));
    }

    private static Stream input() {
        return Stream.of(
                Arguments.of("london", "London"),
                Arguments.of("loNDON", "London"),
                Arguments.of("AmStErDaM", "Amsterdam"),
                Arguments.of("HELSINKI", "Helsinki"),
                Arguments.of("lUblin", "Lublin")
        );
    }
}
