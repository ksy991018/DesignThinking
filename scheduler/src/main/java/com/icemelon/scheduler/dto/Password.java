package com.icemelon.scheduler.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Password {

    @Column(name="password")
    private String password;

    public Password() {}

    private Password(String pass) {

        this.password = pass;

    }

    public static Password from(String pass) {

        return new Password(pass);
    }

    @Override
    public boolean equals(Object obj) {


        if (!(obj instanceof  Password)) return false;

        else return ((Password) obj).password.equals(this.password);
    }

    @Override
    public String toString() {


        return this.password;
    }
}
