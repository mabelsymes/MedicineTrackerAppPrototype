package com.example.myibdtrackerappattempt2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseHelper";
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_MED_INFO = "COLUMN_MED_INFO";
    public static final String COLUMN_SHOW_INFO = "COLUMN_SHOW_INFO";
    public static final String COLUMN_DATE = "COLUMN_DATE";
    public static final String COLUMN_ISRED = "COLUMN_ISRED";
    public static final String COLUMN_ISORANGE = "COLUMN_ISORANGE";
    public static final String COLUMN_ISYELLOW = "COLUMN_ISYELLOW";
    private static final String COLUMN_ISGREEN = "COLUMN_ISGREEN";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "ibdTrackerInfo.db", null, 1);
    }

    // Runs first time this is called
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DATE + " TEXT, " + COLUMN_ISRED + " BOOL, " + COLUMN_ISORANGE + " BOOL, " + COLUMN_ISYELLOW + " BOOL, " + COLUMN_ISGREEN + " BOOL)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addOne(Calendar_cell calendar_cell) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, String.valueOf(calendar_cell.getDay()) + "-" + String.valueOf(calendar_cell.getMonth()) + "-" + String.valueOf(calendar_cell.getYear()));
        cv.put(COLUMN_ISRED, calendar_cell.getRed());
        cv.put(COLUMN_ISORANGE, calendar_cell.getOrange());
        cv.put(COLUMN_ISYELLOW, calendar_cell.getYellow());

        Log.d(TAG, "addOne: About to insert");
        db.insert(CUSTOMER_TABLE,null,cv);
    }

    public ArrayList<Calendar_cell> getData(){
        Log.d(TAG, "getData: Getting data");
        ArrayList<Calendar_cell> returnList = new ArrayList<>();
        Log.d(TAG, "getData: before query");
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        Log.d(TAG, "getData: After select");
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d(TAG, "getData: Before query");
        Cursor cursor = db.rawQuery(queryString, null);
        Log.d(TAG, "getData: Before cursor");
        if (cursor.moveToFirst()){
            // loop through and create new customer object for each row
            Log.d(TAG, "getData: WOOOORKING");
            do {
                int dateID = cursor.getInt(0);

                String date = cursor.getString(1);
                String[] realDate = date.toString().split("-");
                int day = Integer.valueOf(realDate[0]);
                int month = Integer.valueOf(realDate[1]);
                int year = Integer.valueOf(realDate[2]);

                boolean isRed = cursor.getInt(2) == 1 ? true: false;
                boolean isOrange = cursor.getInt(3) == 1 ? true: false;
                boolean isYellow = cursor.getInt(4) == 1 ? true: false;
                boolean isGreen = cursor.getInt(5) == 1 ? true: false;

                Calendar_cell calendar_cell = new Calendar_cell(day, month, year, isRed, isOrange, isYellow, isGreen);
                returnList.add(calendar_cell);

            } while (cursor.moveToNext());
        } else {
            // failure. do not add anything
        }
        cursor.close();
        db.close();
        Log.d(TAG, "getData: RETURNING");
        return returnList;
    }

    public void editOne(Calendar_cell calendar_cell){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String date = String.valueOf(calendar_cell.getDay()) + "-" + String.valueOf(calendar_cell.getMonth()) + "-" + String.valueOf(calendar_cell.getYear());
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_ISRED, calendar_cell.getRed());
        cv.put(COLUMN_ISORANGE, calendar_cell.getOrange());
        cv.put(COLUMN_ISYELLOW, calendar_cell.getYellow());
        db.update(CUSTOMER_TABLE, cv, "COLUMN_DATE = ?", new String[] {date});
    }
}
