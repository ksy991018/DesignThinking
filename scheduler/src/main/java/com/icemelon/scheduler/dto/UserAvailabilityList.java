package com.icemelon.scheduler.dto;

import com.icemelon.scheduler.entity.User;

import java.util.LinkedList;
import java.util.List;

public class UserAvailabilityList {

    private List<UserAvailability> list = new LinkedList<>();

    public UserAvailabilityList(List<UserAvailability> list) {

        this.list = list;
    }
}
