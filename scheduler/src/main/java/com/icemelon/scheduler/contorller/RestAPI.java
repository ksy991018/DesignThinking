package com.icemelon.scheduler.contorller;

import com.icemelon.scheduler.dto.Password;
import com.icemelon.scheduler.dto.ScheduleInfo;
import com.icemelon.scheduler.dto.UniqueCode;
import com.icemelon.scheduler.dto.UserId;
import com.icemelon.scheduler.dto.validator.IScheduleInfoValidator;
import com.icemelon.scheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class RestAPI {

    @Autowired
    private IScheduleInfoValidator scheduleInfoValidator;

    @Autowired
    private SchedulerService service;

    @PutMapping("/schedule") // create schedule with basic info (minTime, maxTime, timeInterval)  and returns schedule unique code
    public ResponseEntity<UniqueCode> createSchedule(@RequestBody ScheduleInfo info) {

        if (!scheduleInfoValidator.validate(info)) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        UniqueCode code = service.createSchedule(info);

        return new ResponseEntity<UniqueCode>(code, HttpStatus.OK);

    }

    @RequestMapping(value = "auth/{code}/{userId}",method =  RequestMethod.GET)
    public ResponseEntity<Void> loginUser(@PathVariable("userId") String userId, @PathVariable("code") String code,
                                          @RequestBody Password password, HttpSession session) {

        UserId id = UserId.of(userId);

        UniqueCode uniqueCode = UniqueCode.fromString(code);

        try {

            service.loginUser(uniqueCode, id, password, session);

        } catch (Exception e) {
            //TODO - catch UserNotFoundException - 404 not found,  WrongPasswordException-  Auth failed

        }
    }

    /*@GetMapping("/{code}/user") -> all registered users
    * @GetMapping("/{code}/user/{userid}") -> login for specific user
    * @GetMapping("/{code}") -> all info for schedule {code}
    *
    *
    * Authentication filtered on AuthFilter
    * @GetMapping("/{code}/info") -> minTime, maxTime, timeInterval, availableDOWs
    * @GetMapping("/{code}/availability") => availabilities for all registered ids
    * @GetMapping("/{code}/availability/{userid}") => availability for specific userid
    * @GetMapping("/{code}/result") => returns list of available time with count
    *
    * */
}
