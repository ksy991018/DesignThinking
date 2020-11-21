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

    public NickName getName() {

        return this.name;
    }

    public void setName(NickName name) {

        this.name = name;
    }

    public UniqueCode getUniqueCode() {return this.uniqueCode;}


    public void setUniqueCode(UniqueCode code) {this.uniqueCode = code;}
    @Override
    public int hashCode() {


        return name.hashCode() + uniqueCode.hashCode();
    }

    public static UserId of(UniqueCode code, NickName name) {

        if (name == null) name = NickName.of("");

        if (code == null) code = new EmptyCode();

        return new UserId(name, code);
    }
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof UserId)) return false;

        UserId id = (UserId)obj;

        return name.equals(id.name) && uniqueCode.equals(id.uniqueCode);
    }
}
