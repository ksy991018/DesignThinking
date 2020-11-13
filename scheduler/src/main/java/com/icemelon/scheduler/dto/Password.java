package com.icemelon.scheduler.dto;

public class Password {


    private String password;

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
