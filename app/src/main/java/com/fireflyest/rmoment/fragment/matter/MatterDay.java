package com.fireflyest.rmoment.fragment.matter;

public class MatterDay {

    private int day;
    private int dayOfWeek;

    public MatterDay(int day, int dayOfWeek) {
        this.day = day;
        this.dayOfWeek = dayOfWeek;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
