package com.icemelon.scheduler.dto;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ScheduleInfo {

    private int minTime;

    private int maxTime;

    private int timeInterval;

    private Set<Integer>  avaialableDOW= new TreeSet();

    public int getMinTime() {

        return minTime;
    }

    public void setMinTime(int minTime) {

        this.minTime = minTime;
    }

    public Set<Integer> getAvaialableDOW() {
        return avaialableDOW;
    }

    public void setAvaialableDOW(Set<Integer> avaialableDOW) {
        this.avaialableDOW = avaialableDOW;
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
