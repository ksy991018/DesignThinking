package com.icemelon.scheduler.dto;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserId implements Serializable {

    private NickName name;

    private UniqueCode uniqueCode;


    private UserId(NickName name , UniqueCode code) {

        this.name = name;

        this.uniqueCode = code;
    }

    public UserId() {

    }

    public UniqueCode getUniqueCode() {return this.uniqueCode;}


    @Override
    public int hashCode() {


        return name.hashCode() + uniqueCode.hashCode();
    }

    public static UserId of(UniqueCode code, NickName name) {

        return new UserId(name, code);
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
