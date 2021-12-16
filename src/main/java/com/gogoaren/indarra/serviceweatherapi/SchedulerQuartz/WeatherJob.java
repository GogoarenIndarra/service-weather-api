package com.gogoaren.indarra.serviceweatherapi.SchedulerQuartz;

import com.gogoaren.indarra.serviceweatherapi.fetch.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class WeatherJob implements Job {

    private final WeatherService weatherService;

    @Autowired
    public WeatherJob(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        List<String> cityNames = (List<String>) jobDataMap.get("cityNames");

        for (String city: cityNames){
            var weather = weatherService.getWeatherByCity(city);
            StringBuilder message = new StringBuilder()
                    .append("Weather for ")
                    .append(city)
                    .append(" at time: ")
                    .append(Instant.now())
                    .append(" weather:  ")
                    .append(weather);

            log.info(message.toString());
        }
    }
}
