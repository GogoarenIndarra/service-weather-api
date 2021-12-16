package com.gogoaren.indarra.serviceweatherapi.SchedulerQuartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class SchedulerService {

    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public <T extends Job> void schedule(final Class<T> jobClass, final TimerInfo info) {
        final JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass, info);
        final Trigger trigger = TimerUtils.buildCronTrigger(jobClass, info);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init(){
        try {
            scheduler.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void preDestroy(){
        try {
            scheduler.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
