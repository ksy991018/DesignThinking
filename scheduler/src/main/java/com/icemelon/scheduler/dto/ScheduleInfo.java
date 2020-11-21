package com.icemelon.scheduler.dto;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Embeddable
public class ScheduleInfo {

    private int minTime; //minute 00 ~ 24 * 60

    private int maxTime; //host

    private int timeInterval;

    private int title;

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    @ElementCollection //jpa annotation
    @CollectionTable(name="available_dow" , joinColumns = @JoinColumn(name = "schedule_code"))
    @Column(name = "dow")
    private Set<Integer> availableDOW = new TreeSet<>(); // day of week (요일)

    public int getTimeBlocks() {

        return (maxTime - minTime) / timeInterval;
    }

    public boolean matches(Availability availability) {

        return availability.getAvailableTimeBlock() < getTimeBlocks() && availableDOW.contains(availability.getDayOfWeek());
    }

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
