package com.conways.calendar;

/**
 * Created by Conways on 2017/3/3.
 */

public class DateEntity {

    private int data;
    private boolean inMonth;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }


    public boolean isInMonth() {
        return inMonth;
    }

    public void setInMonth(boolean inMonth) {
        this.inMonth = inMonth;
    }
}
