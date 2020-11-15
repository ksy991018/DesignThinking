package com.icemelon.scheduler.dto.validator;


import com.icemelon.scheduler.dto.ScheduleInfo;
import org.springframework.stereotype.Component;


@Component
public class ScheduleInfoVaildator implements  IScheduleInfoValidator{

    private final  int MIN_INTERVAL = 30;

    private final int MAX_MINUTE  = 24*60;


    @Override
    public boolean validate(ScheduleInfo info) {

        if (info.getMaxTime() <= info.getMinTime()) return false;

        if (info.getMaxTime() > MAX_MINUTE|| info.getMinTime() < 0) return false;

        if (info.getTimeInterval() < MIN_INTERVAL) return false;

        int max = info.getMaxTime();

        int min = info.getMinTime();

        int gap  = (max - min);

        int interval = info.getTimeInterval();

        if (gap  % interval != 0)
            info.setMaxTime(Math.min(MAX_MINUTE , max + interval - (gap % interval)));

        return true;

    }
}
