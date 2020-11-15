package com.icemelon.scheduler.entity;

import com.icemelon.scheduler.dto.*;

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

    public UserAvailability getUserAvailability() {

        return new UserAvailability(id.getName().toString(), availabilities);
    }
    public void markResult(List<Result> results) {

        NickName name = id.getName();

        for (Result result : results) {

            if (this.availabilities.contains(result.availability()))  result.markAvailable(name);

            else result.markUnAvailable(name);
        }

    }

    public boolean matchPassword(Password pass) {

        return password.equals(pass);
    }
    public UserId getId() {

        return this.id;
    }
}
