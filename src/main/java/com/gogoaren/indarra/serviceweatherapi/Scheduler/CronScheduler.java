package com.gogoaren.indarra.serviceweatherapi.Scheduler;

import com.gogoaren.indarra.serviceweatherapi.fetch.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;

import static com.gogoaren.indarra.serviceweatherapi.utils.RandomCitiesGenerator.getRandomCity;

@Slf4j
@Component
public class CronScheduler {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private Clock clock;

    @Scheduled(fixedDelayString = "${weather.schedule.update.ms}")
    private void uploadWeatherScheduleTask() {
        uploadWeather(getRandomCity());
    }

    @Scheduled(cron = "${weather.schedule.cron.ms}")
    private void uploadWeatherScheduleSpainCities() {
        uploadWeather(getRandomCity());
    }

    private void uploadWeather(final String city) {
        final var weather = weatherService.getWeatherByCity(city);
        log.info("Time: {}, Weather for {}: {}", Instant.now(clock), city, weather);
    }
}
