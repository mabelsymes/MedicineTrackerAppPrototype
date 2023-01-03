package com.example.medicineTrackerApp;

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

    private int day = 0, month = 0, year = 0, fortnightNumber;
    private int reds, oranges, yellows, greens, blues, purples, pinks, whites;
    private Boolean isRed, isOrange, isYellow, isGreen, isBlue, isPurple, isPink, isWhite;

    public Calendar_cell(int day, int month, int year, boolean isRed, boolean isOrange, boolean isYellow, boolean isGreen, boolean isBlue, boolean isPurple, boolean isPink, boolean isWhite, int fortnightNumber, int reds, int oranges, int yellows, int greens, int blues, int purples, int pinks, int whites){
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
        this.fortnightNumber = fortnightNumber;
        this.reds = reds;
        this.oranges = oranges;
        this.yellows = yellows;
        this.greens = greens;
        this.blues = blues;
        this.purples = purples;
        this.pinks = pinks;
        this.whites = whites;
    }

    public int getReds() {
        return reds;
    }

    public void setReds(int reds) {
        this.reds = reds;
    }

    public int getOranges() {
        return oranges;
    }

    public void setOranges(int oranges) {
        this.oranges = oranges;
    }

    public int getYellows() {
        return yellows;
    }

    public void setYellows(int yellows) {
        this.yellows = yellows;
    }

    public int getGreens() {
        return greens;
    }

    public void setGreens(int greens) {
        this.greens = greens;
    }

    public int getBlues() {
        return blues;
    }

    public void setBlues(int blues) {
        this.blues = blues;
    }

    public int getPurples() {
        return purples;
    }

    public void setPurples(int purples) {
        this.purples = purples;
    }

    public int getPinks() {
        return pinks;
    }

    public void setPinks(int pinks) {
        this.pinks = pinks;
    }

    public int getWhites() {
        return whites;
    }

    public void setWhites(int whites) {
        this.whites = whites;
    }

    public int getFortnightNumber() {
        return fortnightNumber;
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

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}