package com.ozge.medicinereminder.ui.home;

public class TimeItem {
    private int hour;
    int minute;
    int posInRecyclerView;

    public TimeItem(int hour, int minute, int posInRecyclerView) {
        this.hour = hour;
        this.minute = minute;
        this.posInRecyclerView = posInRecyclerView;

        //saat ve dakika ögeleri
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getPosInRecyclerView() {
        return posInRecyclerView;
    }
}
