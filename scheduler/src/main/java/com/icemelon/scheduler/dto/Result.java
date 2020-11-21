package com.icemelon.scheduler.dto;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Result implements Comparable<Result>{

    public static List<Result> allAvailable(ScheduleInfo info) {

        List<Result> ret = new LinkedList<>();

        for (int dow : info.getAvailableDOW()) {

            for (int i = 0; i <= info.getTimeBlocks(); i++) {

                Availability availability = new Availability(dow, i);

                ret.add(new Result(availability));
            }
        }

        return ret;
    }

    private Availability availability;

    private Set<String> availableUser = new HashSet<>();

    private Set<String> unavailableUser = new HashSet<>();

    public Result(Availability availability) {

        this.availability = availability;

    }

    public Availability availability() {

        return availability;
    }
    @Override
    public int compareTo(Result o) {

        return ((Integer) o.availableUser.size()).compareTo(availableUser.size());

    }

    public int availableSize() { return availableUser.size();}


    public void markAvailable(NickName name) {

       availableUser.add(name.toString());
    }

    public void markUnAvailable(NickName name) {

        unavailableUser.add(name.toString());
    }
}
