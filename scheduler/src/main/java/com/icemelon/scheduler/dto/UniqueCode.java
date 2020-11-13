package com.icemelon.scheduler.dto;

public class UniqueCode {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {

        return code.toString();
    }

    private UniqueCode(String code) {

        this.code = code;

    }
    public static UniqueCode fromString(String code) {

        return new UniqueCode(code);
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof UniqueCode )) return false;

        return ((UniqueCode) obj).code.equals(code);
    }
}
