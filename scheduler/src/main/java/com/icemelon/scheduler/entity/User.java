package com.icemelon.scheduler.entity;

import com.icemelon.scheduler.dto.Availability;
import com.icemelon.scheduler.dto.Password;
import com.icemelon.scheduler.dto.UserId;

import javax.persistence.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @EmbeddedId
    private UserId id;

    @Embedded
    private Password password;

    @ElementCollection
    @CollectionTable(name = "availability")
    private List<Availability> availabilities = new LinkedList<>();

    public User() {}

    public User(UserId id, Password password) {

        this.id = id;

        this.password = password;

    }

    public List<Availability> getAvailabilities() {return this.availabilities;}

    public void setAvailabilities(List<Availability> availabilities) {

        this.availabilities = availabilities;
    }

    public boolean matchPassword(Password pass) {

        return password.equals(pass);
    }
    public UserId getId() {

        return this.id;
    }
}
