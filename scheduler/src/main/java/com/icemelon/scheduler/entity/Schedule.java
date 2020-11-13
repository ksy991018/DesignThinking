package com.icemelon.scheduler.entity;


import com.icemelon.scheduler.dto.Password;
import com.icemelon.scheduler.dto.SessionToken;
import com.icemelon.scheduler.dto.UniqueCode;
import com.icemelon.scheduler.dto.UserId;
import com.icemelon.scheduler.entity.strategy.ILoginStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Schedule {


    private UniqueCode code;

    private  List<User> userList = new ArrayList<>();

    private ILoginStrategy loginStrategy;


    public SessionToken login(UserId id ,Password pass) throws Exception {

        loginStrategy.login(userList, id, pass);

        return SessionToken.of(code, id);
    }




}
