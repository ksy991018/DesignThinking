package com.icemelon.scheduler.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UniqueCode implements Serializable {


    @Column(name="code" , nullable = false, length = 50)
    private String code;

    public UniqueCode() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {

        return code.toString();
    }

    private UniqueCode(String code) {

        this.code = code;

    }
    public static UniqueCode fromString(String code) {

        return new UniqueCode(code);
    }

    @Override
    public int hashCode() {

        return code.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof UniqueCode )) return false;

        return ((UniqueCode) obj).code.equals(code);
    }
}
