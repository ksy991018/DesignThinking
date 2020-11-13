package com.icemelon.scheduler.dto;


import javax.persistence.Embeddable;

@Embeddable
public class UserId {

    private String id;

    private UserId(String id) {

        this.id = id;
    }

    public UserId() {

    }

    public static UserId of(String id) {

        return new UserId(id);
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
