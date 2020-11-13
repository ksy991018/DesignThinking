package com.icemelon.scheduler.service;


import com.icemelon.scheduler.dto.*;
import com.icemelon.scheduler.entity.Schedule;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SchedulerService {


    private ICodeGenerator codeGenerator; //maybe uuid or something

    public ScheduleInfo getScheduleInfo(UniqueCode code) {


    }

    public UniqueCode createSchedule(ScheduleInfo info) {


    }

    public void loginUser(UniqueCode code, UserId id, Password pass, HttpSession session) { // TODO - throws LoginException when failed;

        Schedule schedule;

        SessionToken token = schedule.login(id, pass);

        token.insertToSession(session);

        //get SessionToken from Scheduler Entity and check it in filter;
    }
}
