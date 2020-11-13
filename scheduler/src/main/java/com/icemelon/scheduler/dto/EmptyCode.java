package com.icemelon.scheduler.dto;

public class EmptyCode extends UniqueCode {

    Object obj = new Object();

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String getCode() {

        return "";
    }

    @Override
    public String toString() {

        return super.toString();

    }

    @Override
    public int hashCode() {

        return obj.hashCode();

    }
}
