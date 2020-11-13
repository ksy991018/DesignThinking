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

        String temp = (String) session.getAttribute("code");

        UniqueCode code;

        if (temp == null) code = new EmptyCode();

        else code = UniqueCode.fromString(temp);

        temp = (String) session.getAttribute("name");

        NickName name = NickName.of(temp == null ? "" : temp);

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
