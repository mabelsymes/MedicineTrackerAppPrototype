package com.example.myibdtrackerappattempt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Calendar_cell {

    private static final String TAG = "Calendar_cell";

    private int day = 0, month = 0, year = 0;
    private Boolean isRed;
    private Context Cal_cellContext;

    public Calendar_cell(){
        this.isRed = false;
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public Boolean getRed() {
        return isRed;
    }

    public void setRed(Boolean red) {
        isRed = red;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}