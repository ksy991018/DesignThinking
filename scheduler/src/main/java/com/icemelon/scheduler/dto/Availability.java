package com.icemelon.scheduler.dto;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class Availability {

    private int dayOfWeek; //요일

    private int availableTimeBlock;

    public Availability(int dayOfWeek, int availableTimeBlock) {

        this.dayOfWeek = dayOfWeek;

        this.availableTimeBlock = availableTimeBlock;
    }

    public Availability() {

    }
    public int getDayOfWeek() {
        return dayOfWeek;
    }


    public int getAvailableTimeBlock() {
        return availableTimeBlock;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setAvailableTimeBlock(int availableTimeBlock) {
        this.availableTimeBlock = availableTimeBlock;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Availability)) return false;

        Availability av = (Availability) obj;

        return av.dayOfWeek == dayOfWeek && av.availableTimeBlock == availableTimeBlock;
    }
}
