package com.icemelon.scheduler.dto;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class SessionToken {

    private UniqueCode code;

    private UserId userId;

    private SessionToken(UniqueCode code,  UserId id) {

        this.code = code;

        userId = id;
    }

    public static SessionToken of(UniqueCode code,  UserId id) {

        return new SessionToken(code, id);

    }

    public static SessionToken fromSession(HttpSession session) {

        UniqueCode code = UniqueCode.fromString((String) session.getAttribute("code"));

        UserId id = UserId.of((String) session.getAttribute("id"));

        return new SessionToken(code, id);
    }
    public UniqueCode getCode() {

        return code;
    }

    public UserId getId() {

        return userId;
    }

    public void insertToSession(HttpSession session) {

        session.setAttribute("code" , code.toString());

        session.setAttribute("id", userId.toString());
    }



}
