package com.gogoaren.indarra.serviceweatherapi.Scheduler;

import com.gogoaren.indarra.serviceweatherapi.fetch.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.gogoaren.indarra.serviceweatherapi.utils.RandomCitiesGenerator.getRandomCity;

@Component
@Slf4j
public class CronScheduler {

    @Autowired
    private WeatherService weatherService;

    @Scheduled(fixedDelayString = "${weather.schedule.update.ms}")
    private void uploadWeatherScheduleTask() {
        uploadWeather(getRandomCity());
    }

    @Scheduled(cron = "${weather.schedule.cron.ms}")
    private void uploadWeatherScheduleSpainCities() {
        uploadWeather(getRandomCity());
        uploadWeather(getRandomCity());
        uploadWeather(getRandomCity());
    }

    private void uploadWeather(String city) {
        var weather = weatherService.getWeatherByCity(city);
        log.info("Weather for " + city + " at time: " + Instant.now() + " weather:  " + weather);
    }
}
