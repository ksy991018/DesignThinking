package com.icemelon.scheduler.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class NickName implements Serializable {


    @Column(name="nickname", length = 20)
    private String nickname;

    private NickName(String name) {

        this.nickname = name;
    }

    public NickName() {

    }

    public static NickName of(String name) {

        return new NickName(name);
    }
    @Override
    public String toString() {

        return nickname;
    }

    @Override
    public int hashCode() {
        return nickname.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof NickName)) return false;

        return ((NickName) obj).nickname.equals(nickname);
    }
}
