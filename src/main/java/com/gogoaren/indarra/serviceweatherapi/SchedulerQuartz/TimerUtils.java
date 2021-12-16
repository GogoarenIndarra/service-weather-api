package com.gogoaren.indarra.serviceweatherapi.SchedulerQuartz;

import lombok.NoArgsConstructor;
import org.quartz.*;

@NoArgsConstructor
public final class TimerUtils {

    public static JobDetail buildJobDetail(final Class jobClass, final TimerInfo info) {

        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("cityNames", info.getCityNames());

        return JobBuilder
                .newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }

    public static CronTrigger buildCronTrigger(final Class jobClass, final TimerInfo info) {
        return TriggerBuilder
                .newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .withSchedule(CronScheduleBuilder.cronSchedule(info.getCronExpression()))
                .build();
    }
}
