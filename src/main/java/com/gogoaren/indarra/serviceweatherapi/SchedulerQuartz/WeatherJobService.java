package com.gogoaren.indarra.serviceweatherapi.SchedulerQuartz;

import com.gogoaren.indarra.serviceweatherapi.Scheduler.CronScheduler;
import com.gogoaren.indarra.serviceweatherapi.utils.RandomCitiesGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.gogoaren.indarra.serviceweatherapi.utils.RandomCitiesGenerator.*;

@Service
public class WeatherJobService {
    private final SchedulerService scheduler;


    @Autowired
    public WeatherJobService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void runWeatherJob() {
        final TimerInfo info = new TimerInfo();
        info.setCronExpression("0 0/20 * ? * *");
        info.setCityNames(getCityList());
        scheduler.schedule(WeatherJob.class, info);
    }
}
