package com.example.myibdtrackerappattempt2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utils {

    private static final String TAG = "Utils";
    private static final String KEY_KEY = "key_key";
    private static final String SHOW_KEY = "show_key";
    private static Utils instance;
    private SharedPreferences sharedPreferences;
    private Context utilsContext;

    public Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("database", Context.MODE_PRIVATE);
        this.utilsContext = context;

        if (null == getKey()){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();
            Gson gson = new Gson();
            ArrayList<String> key = new ArrayList<>();
            for (int i = 0; i < 8; i++){
                key.add("Fill in");
            }
            editor.putString(KEY_KEY, gson.toJson(key));
            editor.commit();
        }

        if (null == getShow()){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();
            Gson gson  = new Gson();
            ArrayList<Boolean> show = new ArrayList<>();
            for (int i=0; i<8; i++){
                show.add(false);
            }
            editor.putString(SHOW_KEY, gson.toJson(show));
            editor.commit();
        }
    }

    public static synchronized Utils getInstance(Context context) {

        if (null != instance) {
            return instance;
        } else {
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Calendar_cell> getDays() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this.utilsContext);
        ArrayList<Calendar_cell> days = dataBaseHelper.getData();
        return days;
    }

    public ArrayList<String> getKey() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> key = gson.fromJson(sharedPreferences.getString(KEY_KEY, null), type);
        return key;
    }

    public ArrayList<Boolean> getShow(){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Boolean>>() {}.getType();
        ArrayList<Boolean> show = gson.fromJson(sharedPreferences.getString(SHOW_KEY, null), type);
        return show;
    }

    public void updateKey(int position, String text) {
        ArrayList<String> key = getKey();
        key.set(position, text);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gson = new Gson();
        editor.putString(KEY_KEY, gson.toJson(key));
        editor.commit();
    }

    public void updateShow(int position, Boolean b){
        ArrayList<Boolean> show = getShow();
        show.set(position, b);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gson = new Gson();
        editor.putString(SHOW_KEY, gson.toJson(show));
        editor.commit();
    }

    public Calendar_cell getDay(int day, int month, int year) {
        ArrayList<Calendar_cell> days = getDays();
        Calendar_cell toReturn = new Calendar_cell(0,0,0,false,false,false, false, false, false, false, false, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        if (null != days) {
            for (Calendar_cell c: days){
                if (c.getYear() == year) {
                    if (c.getMonth() == month) {
                        if (c.getDay() == day) {
                            int position = days.indexOf(c);
                            toReturn = c;
                            break;
                        }
                    }
                }
            }
        }
        return toReturn;
    }

    public String addDay(Calendar_cell day) {
        if (getDay(day.getDay(),day.getMonth(),day.getYear()).getDay() == 0) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this.utilsContext);
            dataBaseHelper.addOne(day);
            return("new");
        } else {
            return("old");
        }
    }

    public void editDay(Calendar_cell changeDay){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this.utilsContext);
        dataBaseHelper.editOne(changeDay);
    }

    public int getFortnightNumber(int day, int month, int year) {
        Date date1 = new Date(year, 1, 1);
        Date date2 = new Date(year, month, day);
        long dateBefore = date1.getTime();
        long dateAfter = date2.getTime();
        long days = (dateAfter - dateBefore);
        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
        int d = Integer.valueOf(String.valueOf(days));
        return d/14;
    }

    public void updateFortnight(int day, int month, int year, ArrayList<String> toAdd, ArrayList<String> toRemove){
        int fortnightNumber = getFortnightNumber(day, month, year);
        int dayValue = fortnightNumber + 101;
        Calendar_cell fortnight = getDay(dayValue, 1, year);
        if (fortnight.getDay() != 0) {
            for (String s : toAdd) {
                if (s.equals("Red")) {
                    fortnight.setReds(fortnight.getReds() + 1);
                    if (fortnight.getReds() >= 7) {
                        fortnight.setRed(true);
                    }
                }
                if (s.equals("Orange")) {
                    fortnight.setOranges(fortnight.getOranges() + 1);
                    if (fortnight.getOranges() >= 7) {
                        fortnight.setOrange(true);
                    }
                }
                if (s.equals("Yellow")) {
                    fortnight.setYellows(fortnight.getYellows() + 1);
                    if (fortnight.getYellows() >= 7) {
                        fortnight.setYellow(true);
                    }
                }
                if (s.equals("Green")) {
                    fortnight.setGreens(fortnight.getGreens() + 1);
                    if (fortnight.getGreens() >= 7) {
                        fortnight.setGreen(true);
                    }
                }
                if (s.equals("Blue")) {
                    fortnight.setBlues(fortnight.getBlues() + 1);
                    if (fortnight.getBlues() >= 7) {
                        fortnight.setBlue(true);
                    }
                }
                if (s.equals("Purple")) {
                    fortnight.setPurples(fortnight.getPurples() + 1);
                    if (fortnight.getPurples() >= 7) {
                        fortnight.setPurple(true);
                    }
                }
                if (s.equals("Pink")) {
                    fortnight.setPinks(fortnight.getPinks() + 1);
                    if (fortnight.getPinks() >= 7) {
                        fortnight.setPink(true);
                    }
                }
                if (s.equals("White")) {
                    fortnight.setWhites(fortnight.getWhites() + 1);
                    if (fortnight.getWhites() >= 7) {
                        fortnight.setWhite(true);
                    }
                }
            }
            for (String s : toRemove) {
                if (s.equals("Red")) {
                    fortnight.setReds(fortnight.getReds() - 1);
                    if (fortnight.getReds() < 7) {
                        fortnight.setRed(false);
                    }
                }
                if (s.equals("Orange")) {
                    fortnight.setOranges(fortnight.getOranges() - 1);
                    if (fortnight.getOranges() < 7) {
                        fortnight.setOrange(false);
                    }
                }
                if (s.equals("Yellow")) {
                    fortnight.setYellows(fortnight.getYellows() - 1);
                    if (fortnight.getYellows() < 7) {
                        fortnight.setYellow(false);
                    }
                }
                if (s.equals("Green")) {
                    fortnight.setGreens(fortnight.getGreens() - 1);
                    if (fortnight.getGreens() < 7) {
                        fortnight.setGreen(false);
                    }
                }
                if (s.equals("Blue")) {
                    fortnight.setBlues(fortnight.getBlues() - 1);
                    if (fortnight.getBlues() < 7) {
                        fortnight.setBlue(false);
                    }
                }
                if (s.equals("Purple")) {
                    fortnight.setPurples(fortnight.getPurples() - 1);
                    if (fortnight.getPurples() < 7) {
                        fortnight.setPurple(false);
                    }
                }
                if (s.equals("Pink")) {
                    fortnight.setPinks(fortnight.getPinks() - 1);
                    if (fortnight.getPinks() < 7) {
                        fortnight.setPink(false);
                    }
                }
                if (s.equals("White")) {
                    fortnight.setWhites(fortnight.getWhites() - 1);
                    if (fortnight.getWhites() < 7) {
                        fortnight.setWhite(false);
                    }
                }
            }
            editDay(fortnight);
        } else {
            fortnight = new Calendar_cell(dayValue, 1, year, false, false, false, false, false, false, false, false, getFortnightNumber(day, month, year), 0, 0, 0, 0, 0, 0, 0, 0);
            for (String s : toAdd) {
                if (s.equals("Red")) {
                    fortnight.setReds(1);
                }
                if (s.equals("Orange")) {
                    fortnight.setOranges(1);
                }
                if (s.equals("Yellow")) {
                    fortnight.setYellows(1);
                }
                if (s.equals("Green")) {
                    fortnight.setGreens(1);
                }
                if (s.equals("Blue")) {
                    fortnight.setBlues(1);
                }
                if (s.equals("Purple")) {
                    fortnight.setPurples(1);
                }
                if (s.equals("Pink")) {
                    fortnight.setPinks(1);
                }
                if (s.equals("White")) {
                    fortnight.setWhites(1);
                }
            }
            addDay(fortnight);
        }
    }

    public ArrayList<Calendar_cell> getAllDaysInYear(LocalDate selectedDate){
        int y = selectedDate.getYear();
        ArrayList<Calendar_cell> daysInYear = new ArrayList<>();
        for (int m=1; m<=12; m++){
            LocalDate startDate = LocalDate.of(y, m,1);
            ArrayList<String> daysInMonth = daysInMonthArray(startDate, selectedDate);
            for (String d: daysInMonth) {
                if (!d.equals("")) {
                    int day = Integer.valueOf(d);
                    int fortnightNumber = getFortnightNumber(day, m, y);
                    Calendar_cell calendar_cell = new Calendar_cell(day, m, y, false,false, false, false, false, false, false, false, fortnightNumber, 0, 0, 0, 0, 0, 0, 0, 0);
                    addDay(calendar_cell);
                    daysInYear.add(getDay(calendar_cell.getDay(), calendar_cell.getMonth(), calendar_cell.getYear()));
                }
            }
        }
        return daysInYear;
    }

    public ArrayList<String> daysInMonthArray(LocalDate date, LocalDate selectedDate) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i=1; i<=42; i++){
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    public ArrayList<String> generateWeeksForYear(LocalDate selectedDate) {
        ArrayList<Calendar_cell> daysInYear = getAllDaysInYear(selectedDate);
        int count = 0;
        int count2 = 1;
        int red = 0;
        int orange = 0;
        int yellow = 0;
        int green = 0;
        int blue = 0;
        int purple = 0;
        int pink = 0;
        int white = 0;
        ArrayList<String> weeks = new ArrayList<>();
        for (int i=0; i< daysInYear.size(); i++){
            Calendar_cell day = daysInYear.get(i);
            if (day.getRed()){
                red += 1;
            }
            if (day.getOrange()){
                orange += 1;
            }
            if (day.getYellow()){
                yellow += 1;
            }
            if (day.getGreen()){
                green += 1;
            }
            if (day.getBlue()){
                blue += 1;
            }
            if (day.getPurple()){
                purple +=1;;
            }
            if (day.getPink()){
                pink += 1;
            }
            if (day.getWhite()){
                white += 1;
            }
            if (count == 13){
                count = -1;
                Calendar_cell week = new Calendar_cell(count2 + 100, 1, day.getYear(), false, false, false, false, false, false, false, false, 0, red, orange, yellow, green, blue, purple, pink, white);
                if (red >= 7){
                    week.setRed(true);
                }
                if (orange >= 7){
                    week.setOrange(true);
                }
                if (yellow >= 7){
                    week.setYellow(true);
                }
                if (green >= 7){
                    week.setGreen(true);
                }
                if (blue >= 7){
                    week.setBlue(true);
                }
                if (purple >= 7){
                    week.setPurple(true);
                }
                if (pink >= 7){
                    week.setPink(true);
                }
                if (white >= 7){
                    week.setWhite(true);
                }
                weeks.add(String.valueOf(count2 + 100));
                if (addDay(week).equals("old")){
                    editDay(week);
                }
                count2 += 1;
                red = 0;
                orange = 0;
                yellow = 0;
                green = 0;
                blue = 0;
                purple = 0;
                pink = 0;
                white = 0;
            }
            count += 1;
        }
        return weeks;

    }

}
