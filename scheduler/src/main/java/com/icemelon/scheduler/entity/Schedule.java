package com.icemelon.scheduler.entity;


import com.icemelon.scheduler.dto.Password;
import com.icemelon.scheduler.dto.SessionToken;
import com.icemelon.scheduler.dto.UniqueCode;
import com.icemelon.scheduler.dto.UserId;
import com.icemelon.scheduler.entity.strategy.DefaultLoginStrategy;
import com.icemelon.scheduler.entity.strategy.ILoginStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "schedule")
public class Schedule {


    @EmbeddedId
    private UniqueCode code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="schedule_id")})
    private List<User> userList = new ArrayList<>();

    @Transient
    private ILoginStrategy loginStrategy = new DefaultLoginStrategy();

    public Schedule() {}

    public Schedule(UniqueCode code) {this.code =code;}

    public SessionToken login(UserId id ,Password pass) throws Exception {

        loginStrategy.login(userList, id, pass);

        return SessionToken.of(code, id);
    }




}
