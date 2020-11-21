package com.icemelon.scheduler.contorller;

import com.icemelon.scheduler.dto.*;
import com.icemelon.scheduler.dto.validator.IScheduleInfoValidator;
import com.icemelon.scheduler.exception.LoginException;
import com.icemelon.scheduler.exception.ScheduleNotFoundException;
import com.icemelon.scheduler.exception.UserNotFoundException;
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


    // HTTP ->html -> front (design, form) , backend -> json , create_schedule
    // data transfer object, json -> java object (spring function)
    @PutMapping("/schedule") // create schedule with basic info (minTime, maxTime, timeInterval)  and returns schedule unique code
    public ResponseEntity<UniqueCode> createSchedule(@RequestBody ScheduleInfo info) {

        if (!scheduleInfoValidator.validate(info)) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        UniqueCode code = service.createSchedule(info);

        return new ResponseEntity<UniqueCode>(code, HttpStatus.OK);

    }

    @PostMapping("/schedule/{code}/availability/{id}")
    public ResponseEntity<Void> setAvailability(@RequestBody  AvailabilityList list, @PathVariable("code") String code , @PathVariable("id") String userId) {

        NickName name = NickName.of(userId);

        UniqueCode uniqueCode = UniqueCode.fromString(code);

        UserId id = UserId.of(uniqueCode, name);

        try {

            service.setUserSchedule(id, list);

            return ResponseEntity.ok().build();//200 ok

        } catch(ScheduleNotFoundException | UserNotFoundException e) {

            return ResponseEntity.notFound().build(); //404 not found
        }
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

            return ResponseEntity.notFound().build(); //404

        } catch (LoginException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("schedule/{code}/result")
    public ResponseEntity<ResultList> getResultList(@PathVariable("code") String code) {

        UniqueCode uniqueCode = UniqueCode.fromString(code);

        try {

            return new ResponseEntity<ResultList>(service.getScheduleResult(uniqueCode) , HttpStatus.OK);

        } catch (ScheduleNotFoundException ex) {

            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("schedule/{code}/availability")
    public ResponseEntity<UserAvailabilityList> getAllAvailabilities(@PathVariable("code") String code) {

        UniqueCode uniqueCode = UniqueCode.fromString(code);

        try {

            UserAvailabilityList list = service.getAllAvailabilities(uniqueCode);

            return new ResponseEntity<>(list, HttpStatus.OK);

        } catch (ScheduleNotFoundException exception) {

            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("schedule/{code}/availability/{id}")
    public ResponseEntity<UserAvailability> getAvailability(@PathVariable("code") String code, @PathVariable("id") String name) {

        UniqueCode uniqueCode = UniqueCode.fromString(code);

        NickName nickName = NickName.of(name);

        UserId id = UserId.of(uniqueCode, nickName);

        try {

            return new ResponseEntity<UserAvailability>(service.getUserAvailability(id), HttpStatus.OK);

        } catch (UserNotFoundException | ScheduleNotFoundException ex) {

            return ResponseEntity.notFound().build();

        }
    }
    /*
    *@GetMapping("auth/{code}/{userId}")> login for userId
    *
    * Authentication filtered on AuthFilter
    *
    * schedule -> cr
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
