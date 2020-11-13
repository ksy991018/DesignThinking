package com.icemelon.scheduler.dto;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserId implements Serializable {

    private String id;

    private UserId(String id) {

        this.id = id;
    }

    public UserId() {

    }


    @Override
    public int hashCode() {


        return id.hashCode();
    }

    public static UserId of(String id) {

        return new UserId(id);
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
