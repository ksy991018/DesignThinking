package com.icemelon.scheduler.service;


import com.icemelon.scheduler.dto.*;
import com.icemelon.scheduler.entity.Schedule;
import com.icemelon.scheduler.exception.LoginException;
import com.icemelon.scheduler.exception.ScheduleNotFoundException;
import com.icemelon.scheduler.exception.UserNotFoundException;
import com.icemelon.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class SchedulerService {


    @Autowired
    private ScheduleRepository repository;

    @Autowired
    private ICodeGenerator codeGenerator; //maybe uuid or something


    public ResultList getScheduleResult(UniqueCode code) throws ScheduleNotFoundException {

        Optional<Schedule> schedule = repository.findById(code);

        if (schedule.isEmpty()) throw new ScheduleNotFoundException();

        Schedule instance = schedule.get();

        return instance.getResult();

    }

    public AvailabilityList getAllAvailabilities(UniqueCode code) throws ScheduleNotFoundException {

        Optional<Schedule> schedule = repository.findById(code);

        if (schedule.isEmpty()) throw new ScheduleNotFoundException();

        Schedule instance = schedule.get();

        return instance.getAllAvailabilities();
    }

    public ScheduleInfo getScheduleInfo(UniqueCode code) throws ScheduleNotFoundException {

        Optional<Schedule> schedule = repository.findById(code);

        if (schedule.isEmpty()) throw new ScheduleNotFoundException();

        return schedule.get().getInfo();
    }

    public UserAvailability getUserAvailability(UserId id) throws ScheduleNotFoundException, UserNotFoundException {

        UniqueCode code = id.getUniqueCode();

        Optional<Schedule> schedule = repository.findById(code);

        if (schedule.isEmpty()) throw new ScheduleNotFoundException();

        Schedule instance = schedule.get();

        return instance.getUserAvailability(id);

    }

    public UniqueCode createSchedule(ScheduleInfo info) {

        UniqueCode code = codeGenerator.makeUniqueCode();

        Schedule schedule = new Schedule.ScheduleBuilder().from(info).id(code).build();

        repository.save(schedule);

        return code;
    }

    public void loginUser(UserId id, Password pass, HttpSession session) throws LoginException, ScheduleNotFoundException{ // TODO - throws LoginException when failed;

        Optional<Schedule> optional = repository.findById(id.getUniqueCode());

        if (optional.isEmpty()) throw new ScheduleNotFoundException();

        Schedule schedule = optional.get();

        SessionToken token = schedule.login(id, pass);

        token.insertToSession(session);

    }
}
