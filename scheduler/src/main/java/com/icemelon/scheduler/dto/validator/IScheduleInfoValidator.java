package com.icemelon.scheduler.dto.validator;

import com.icemelon.scheduler.dto.ScheduleInfo;
import org.springframework.stereotype.Component;


public interface IScheduleInfoValidator {

    public boolean validate(ScheduleInfo info);
}
