package com.icemelon.scheduler.dto.validator;

import com.icemelon.scheduler.dto.Password;
import com.icemelon.scheduler.dto.UserId;

public interface UserValidator {
    
    public void validate(UserId id , Password password);

}
