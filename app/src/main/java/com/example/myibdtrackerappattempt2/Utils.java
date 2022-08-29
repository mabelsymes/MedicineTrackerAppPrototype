package com.example.myibdtrackerappattempt2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final String TAG = "Utils";

//    private static final String DAYS_KEY = "days_key";
    private static final String KEY_KEY = "key_key";
    private static Utils instance;
    private SharedPreferences sharedPreferences;
    private Context utilsContext;

    public Utils(Context context) {

//        Log.d(TAG, "Utils: About to get sharedPreferences");
        sharedPreferences = context.getSharedPreferences("database", Context.MODE_PRIVATE);
        this.utilsContext = context;
//        Log.d(TAG, "Utils: Got SharedPreferencesA");

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        Log.d(TAG, "Utils: Putting Days String");
//        editor.putString(DAYS_KEY, gson.toJson(new ArrayList<Calendar_cell>(5)));
//        Log.d(TAG, "Utils: Before commit");
//        editor.commit();

//        if (null == getDays()) {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor = sharedPreferences.edit();
//            Gson gson = new Gson();
//            gson = new Gson();
//            Log.d(TAG, "Utils: Putting Days String");
//            editor.putString(DAYS_KEY, gson.toJson(new ArrayList<Calendar_cell>(5)));
//            Log.d(TAG, "Utils: Before commit");
//            editor.commit();
//        }
//
        if (null == getKey()){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();
            Gson gson = new Gson();
            gson = new Gson();
            ArrayList<String> key = new ArrayList<>();
            for (int i = 0; i < 8; i++){
                key.add("Fill in");
            }
            editor.putString(KEY_KEY, gson.toJson(key));
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
        Log.d(TAG, "getDays: before dataBaseHelper");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this.utilsContext);
        Log.d(TAG, "getDays: CRYING");
        ArrayList<Calendar_cell> days = dataBaseHelper.getData();
        Log.d(TAG, "getDays: after dataBaseHelper");
        return days;
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<Calendar_cell>>() {}.getType();
//        Log.d(TAG, "getDays: After Type");
//        ArrayList<Calendar_cell> days = gson.fromJson(sharedPreferences.getString(DAYS_KEY, null), type);
//        return days;
    }
//
    public ArrayList<String> getKey() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        Log.d(TAG, "getDays: After Type");
        ArrayList<String> key = gson.fromJson(sharedPreferences.getString(KEY_KEY, null), type);
        return key;
    }
//
    public void updateKey(int position, String text) {
        ArrayList<String> key = getKey();
        key.set(position, text);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gson = new Gson();
        editor.putString(KEY_KEY, gson.toJson(key));
        editor.commit();
    }

    public Calendar_cell getDay(int day, int month, int year) {
        ArrayList<Calendar_cell> days = getDays();
        Calendar_cell toReturn = new Calendar_cell(0,0,0,false,false,false);
        if (null != days) {
            Log.d(TAG, "getDay: NOT NULL");
            for (Calendar_cell c: days){
                if (c.getYear() == year) {
                    Log.d(TAG, "getDay: Year is same");
                    if (c.getMonth() == month) {
                        Log.d(TAG, "getDay: Month is same");
                        if (c.getDay() == day) {
                            Log.d(TAG, "getDay: c's red is " + c.getRed());
                            Log.d(TAG, "getDay: c's day is " + c.getDay());
                            int position = days.indexOf(c);
                            Log.d(TAG, "getDay: position is " + position);
                            toReturn = c;
                            break;
                        }
                    }
                }
            }
        }
////        if (toReturn == null) {
////            Log.d(TAG, "getDay: toReturn is null");
////            Calendar_cell newDay = new Calendar_cell();
////            newDay.setDay(day);
////            newDay.setMonth(month);
////            newDay.setYear(year);
////            addDay(newDay);
////            toReturn = newDay;
////        }
        Log.d(TAG, "getDay: Returning... " + toReturn);
        return toReturn;
    }

    public String addDay(Calendar_cell day) {
        if (getDay(day.getDay(),day.getMonth(),day.getYear()).getDay() == 0) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this.utilsContext);
            Log.d(TAG, "addDay: ADDING DAY");
            dataBaseHelper.addOne(day);
            return("new");
        } else {
            return("old");
        }
    }
//
//    public String addDay(Calendar_cell day) {
//        if (getDay(day.getDay(),day.getMonth(),day.getYear()) == null) {
//            Log.d(TAG, "addDay: Adding");
//            Gson gson = new Gson();
//            Type type = new TypeToken<ArrayList<Calendar_cell>>() {
//            }.getType();
//            Log.d(TAG, "addDay: Before gson.fromJson");
//            ArrayList<Calendar_cell> newDays = getDays();
//            Log.d(TAG, "addDay: day is " + day);
//            newDays.add(day);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            gson = new Gson();
//            editor.putString(DAYS_KEY, gson.toJson(newDays));
//            editor.commit();
//            return("new");
//        } else {
//            return("old");
//        }
//    }
//
    public void editDay(Calendar_cell changeDay){
//        ArrayList<Calendar_cell> days = getDays();
//        Calendar_cell toChangeDay = null;
//        for (Calendar_cell d: days) {
//            if (d.getDay() == changeDay.getDay() && d.getMonth() == changeDay.getMonth() && d.getYear() == changeDay.getYear()) {
//                toChangeDay = d;
//            }
//        }
//        int changeDayPosition = days.indexOf(toChangeDay);
//        Log.d(TAG, "editDay: editDay position is " + changeDayPosition);
//        days.set(changeDayPosition,changeDay);
//        Log.d(TAG, "editDay: changeDay red is " + days.get(changeDayPosition).getRed());
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this.utilsContext);
        Log.d(TAG, "addDay: EDITING DAY");
        dataBaseHelper.editOne(changeDay);

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        gson = new Gson();
//        editor.putString(DAYS_KEY, gson.toJson(days));
//        editor.commit();
//        days = getDays();
//        Log.d(TAG, "editDay: changeDay red is " + days.get(changeDayPosition).getRed());
//        Calendar_cell day = getDay(changeDay.getDay(),changeDay.getMonth(),changeDay.getYear());
//        Log.d(TAG, "editDay: dayRed is " + day.getRed());
//        Log.d(TAG, "editDay: length of list is " + days.size());
    }

}
