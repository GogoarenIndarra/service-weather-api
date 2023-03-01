package com.gogoaren.indarra.serviceweatherapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServiceWeatherApiApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ServiceWeatherApiApplication.class, args);
    }

}
