package com.example.myibdtrackerappattempt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calendar_cell {

    private static final String TAG = "Calendar_cell";

    private int day = 0, month = 0, year = 0;
    private Boolean isRed, isOrange, isYellow, isGreen, isBlue, isPurple, isPink, isWhite;
    private Context Cal_cellContext;
    private Button redButton;
    private TextView cellDayText;

    public Calendar_cell(int day, int month, int year, boolean isRed, boolean isOrange, boolean isYellow, boolean isGreen, boolean isBlue, boolean isPurple, boolean isPink, boolean isWhite){
        this.isRed = isRed;
        this.isOrange = isOrange;
        this.isYellow = isYellow;
        this.isGreen = isGreen;
        this.isBlue = isBlue;
        this.isPurple = isPurple;
        this.isPink = isPink;
        this.isWhite = isWhite;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Boolean getBlue() {
        return isBlue;
    }

    public void setBlue(Boolean blue) {
        isBlue = blue;
    }

    public Boolean getPurple() {
        return isPurple;
    }

    public void setPurple(Boolean purple) {
        isPurple = purple;
    }

    public Boolean getPink() {
        return isPink;
    }

    public void setPink(Boolean pink) {
        isPink = pink;
    }

    public Boolean getWhite() {
        return isWhite;
    }

    public void setWhite(Boolean white) {
        isWhite = white;
    }

    public Boolean getGreen() {
        return isGreen;
    }

    public void setGreen(Boolean green) {
        isGreen = green;
    }

    public Boolean getYellow() {
        return isYellow;
    }

    public void setYellow(Boolean yellow) {
        isYellow = yellow;
    }

    public Boolean getOrange() {
        return isOrange;
    }

    public void setOrange(Boolean orange) {
        isOrange = orange;
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