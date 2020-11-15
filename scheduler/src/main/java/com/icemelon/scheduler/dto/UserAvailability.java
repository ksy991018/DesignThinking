package com.icemelon.scheduler.dto;

import java.util.LinkedList;
import java.util.List;

public class UserAvailability {

    private String name;

    private List<Availability> list = new LinkedList<>();

    public UserAvailability(String name,  List<Availability> list) {

        this.name = name;

        this.list = list;
    }

}
