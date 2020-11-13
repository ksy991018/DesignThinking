package com.icemelon.scheduler.contorller;

import com.icemelon.scheduler.dto.*;
import com.icemelon.scheduler.dto.validator.IScheduleInfoValidator;
import com.icemelon.scheduler.exception.LoginException;
import com.icemelon.scheduler.exception.ScheduleNotFoundException;
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
        NickName name = NickName.of(userId);

        UniqueCode uniqueCode = UniqueCode.fromString(code);

        UserId id = UserId.of(uniqueCode, name);

        try {

            service.loginUser(id, password, session);

            return ResponseEntity.ok().build();

        } catch (ScheduleNotFoundException e) {


            return ResponseEntity.notFound().build();

        } catch (LoginException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /*
    *@GetMapping("auth/{code}/{userId}")> login for userId
    *
    * Authentication filtered on AuthFilter
    *
    * @PutMapping("schedule/") -> create schedule with baisc infomation e.g. minTime, maxTime, timeInterval, availableDOWs
    * @GetMapping("schedule/{code}") -> all info for schedule {code}
    * @GetMapping("schedule/{code}/user") -> all registered users
    * @GetMapping("schedule/{code}/info") -> minTime, maxTime, timeInterval, availableDOWs
    * @GetMapping("schedule/{code}/availability") =>  availabilities for all registered user
    * @GetMapping("schedule/{code}/availability/{userid}") => availability for specific user
    * @GetMapping("schedule/{code}/result") => returns list of overlapping availabilities with ranking
    * @PostMapping("schedule/{code}/availability") => mark availability for session user
    * */
}
