package com.gogoaren.indarra.serviceweatherapi.SchedulerQuartz;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TimerInfo {
    private List<String> cityNames;
    private String cronExpression;
}
