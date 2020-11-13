package com.icemelon.scheduler.entity;

import com.icemelon.scheduler.dto.Password;
import com.icemelon.scheduler.dto.UserId;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @EmbeddedId
    private UserId id;

    @Embedded
    private Password password;


    public boolean match(Password pass) {

        return password.equals(pass);
    }
    public UserId getId() {

        return this.id;
    }
}
