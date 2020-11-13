package com.icemelon.scheduler.dto;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class SessionToken {


    private UserId userId;

    private SessionToken(UserId id) {

        userId = id;
    }

    public static SessionToken of(UserId id) {

        return new SessionToken(id);

    }

    public static SessionToken fromSession(HttpSession session) {

        UniqueCode code = UniqueCode.fromString((String) session.getAttribute("code"));

        NickName name = NickName.of((String) session.getAttribute("name"));

        return new SessionToken(UserId.of(code,name));

    }
    public UniqueCode getCode() {

        return userId.getUniqueCode();
    }

    public UserId getId() {

        return userId;
    }

    public void insertToSession(HttpSession session) {

        session.setAttribute("code" , userId.getUniqueCode().toString());

        session.setAttribute("name", userId.toString());
    }



}
