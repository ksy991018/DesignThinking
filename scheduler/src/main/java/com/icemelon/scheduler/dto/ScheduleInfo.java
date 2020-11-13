package com.icemelon.scheduler.dto;

import java.util.Set;
import java.util.TreeSet;

public class ScheduleInfo {

    private int minTime;

    private int maxTime;

    private int timeInterval;

    private Set<Integer> availableDOW = new TreeSet();

    public int getMinTime() {

        return minTime;
    }

    public void setMinTime(int minTime) {

        this.minTime = minTime;
    }

    public Set<Integer> getAvailableDOW() {
        return availableDOW;
    }

    public void setAvailableDOW(Set<Integer> availableDOW) {
        this.availableDOW = availableDOW;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }
}
