package com.gogoaren.indarra.serviceweatherapi.Scheduler;

import com.gogoaren.indarra.serviceweatherapi.fetch.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class Scheduler {

    @Autowired
    WeatherService weatherService;

    @Scheduled(fixedDelayString = "${weather.schedule.update.ms}")
    public void uploadWeatherScheduleTask() {

        List<String> cityNamesB = Arrays.asList(
                "San Sebastian", "Warsaw", "Shanghai", "Alicante", "Albuquerque", "Moscow",
                "Jijona", "Novomoskovsk", "Gebze", "Briançon", "Jeddah", "Santiago de Cali",
                "Port Montt", "Cape Town", "Bilbao", "Rostov", "Cairo", "Lagos", "Gyöngyös",
                "Provincia di Pescara", "Euskirchen");

        Random rand = new Random();
        String randomElement = cityNamesB.get(rand.nextInt(cityNamesB.size()));
        var weather = weatherService.getWeatherByCity(randomElement);
        log.info("Weather for " + randomElement + " at time: " + Instant.now() + " weather:  " + weather);
    }


    @Scheduled(cron = "${weather.schedule.cron.ms}")
    public void uploadLondonWeatherScheduleTask() {
        uploadWeather("Santiago de Cali");
        uploadWeather("Cape Town");
        uploadWeather("Albuquerque");
    }

    private void uploadWeather(String city) {
        var weather = weatherService.getWeatherByCity(city);
        log.info("Weather for " + city + " at time: " + Instant.now() + " weather:  " + weather);
    }
}
