package com.icemelon.scheduler.dto;

public class UserId {

    private String id;

    private UserId(String id) {

        this.id = id;
    }

    public static UserId of(String id) {

        return new UserId(id);
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
