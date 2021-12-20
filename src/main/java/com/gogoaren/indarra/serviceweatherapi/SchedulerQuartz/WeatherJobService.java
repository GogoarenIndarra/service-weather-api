package com.gogoaren.indarra.serviceweatherapi.SchedulerQuartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.stream.Collectors;

import static com.gogoaren.indarra.serviceweatherapi.utils.RandomCitiesGenerator.*;

@Service
public class WeatherJobService {
    private final SchedulerService scheduler;


    @Autowired
    public WeatherJobService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void runWeatherJobForCitiesStartsWithLetterA() {
        final TimerInfo info = new TimerInfo();
        info.setCronExpression(" 0 5 * ? * *"); // At second :00 of minute :05 of every hour
        info.setCityNames(getCityList()
                .stream()
                .filter(x->x.startsWith("A"))
                .collect(Collectors.toList()));
        scheduler.schedule(WeatherJob.class, info);
    }

    @PostConstruct
    public void runWeatherJobForAllCities() {
        final TimerInfo info = new TimerInfo();
        info.setCronExpression("0 0/20 * ? * *"); // At second :00, every 20 minutes starting at minute :00, of every hour
        info.setCityNames(getCityList());
        scheduler.schedule(WeatherJob.class, info);
    }
}
