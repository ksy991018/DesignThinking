package com.icemelon.scheduler.entity;


import com.icemelon.scheduler.dto.*;
import com.icemelon.scheduler.entity.strategy.DefaultLoginStrategy;
import com.icemelon.scheduler.entity.strategy.ILoginStrategy;
import com.icemelon.scheduler.exception.InvalidAvailabilityException;
import com.icemelon.scheduler.exception.LoginException;
import com.icemelon.scheduler.exception.UserNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



@Entity
@Table(name = "schedule")
public class Schedule {

    @EmbeddedId
    private UniqueCode uniqueCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "code", updatable = false, insertable = false)
    private List<User> userList = new ArrayList<>();

    @Transient
    private ILoginStrategy loginStrategy = new DefaultLoginStrategy();

    private ScheduleInfo info;

    public Schedule() {}

    private Schedule(UniqueCode code, ScheduleInfo info) {

        this.info = info;

        this.uniqueCode =code;

    }

    public ScheduleInfo getInfo() {return this.info;}

    public SessionToken login(UserId id ,Password pass) throws LoginException {

        loginStrategy.login(userList, id, pass);

        return SessionToken.of(id);
    }

    public UserAvailabilityList getAllAvailabilities() {

        List<UserAvailability> list = new LinkedList<>();

        for (User user : userList) {

            list.add( user.getUserAvailability());
        }

        return new UserAvailabilityList(list);
    }

    public ResultList getResult() {

        List<Result> list =  Result.allAvailable(this.info);

        for (User user : this.userList) {

            user.markResult(list);
        }

        ResultList resultList = new ResultList(list);

        resultList.sort();

        return resultList;
    }

    public UserAvailability getUserAvailability(UserId id) throws UserNotFoundException {

        User user = findUser(id);

        return user.getUserAvailability();

    }

    private User findUser(UserId id) {

        for (User user : userList) {

            if (user.getId().equals(id)) return user;
        }

        throw new UserNotFoundException();
    }


    public void setUserAvailability(UserId id , List<Availability> availabilities) throws InvalidAvailabilityException, UserNotFoundException{

        User user = findUser(id);

        for (Availability availability : availabilities) {

            if (!info.matches(availability)) throw new InvalidAvailabilityException();
        }

        user.setAvailabilities(availabilities);
    }

    public static class ScheduleBuilder {

        private UniqueCode code;

        private ScheduleInfo info;

        public ScheduleBuilder from(ScheduleInfo info) {

            //TODO - fill it

            this.info = info;

            return this;
        }

        public ScheduleBuilder id(UniqueCode code) {
            this.code = code;

            return this;
        }

        public Schedule build() {

            return new Schedule(code, info);
        }


    }
}
