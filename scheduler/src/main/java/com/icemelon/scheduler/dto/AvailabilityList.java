package com.icemelon.scheduler.dto;

import com.icemelon.scheduler.entity.User;

import java.util.LinkedList;
import java.util.List;

public class AvailabilityList {

    private List<UserAvailability> list = new LinkedList<>();

    public AvailabilityList(List<UserAvailability> list) {

        this.list = list;
    }
}
