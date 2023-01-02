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
    public static final String COLUMN_DATE = "COLUMN_DATE";
    public static final String COLUMN_ISRED = "COLUMN_ISRED";
    public static final String COLUMN_ISORANGE = "COLUMN_ISORANGE";
    public static final String COLUMN_ISYELLOW = "COLUMN_ISYELLOW";
    private static final String COLUMN_ISGREEN = "COLUMN_ISGREEN";
    private static final String COLUMN_ISBLUE = "COLUMN_ISBLUE";
    private static final String COLUMN_ISPURPLE = "COLUMN_ISPURPLE";
    private static final String COLUMN_ISPINK = "COLUMN_ISPINK";
    private static final String COLUMN_ISWHITE = "COLUMN_ISWHITE";
    private static final String COLUMN_FORTNIGHTNUMBER = "COLUMN_FORTNIGHTNUMBER";
    private static final String COLUMN_REDS = "COLUMN_REDS";
    private static final String COLUMN_ORANGES = "COLUMN_ORANGES";
    private static final String COLUMN_YELLOWS = "COLUMN_YELLOWS";
    private static final String COLUMN_GREENS = "COLUMN_GREENS";
    private static final String COLUMN_BLUES = "COLUMN_BLUES";
    private static final String COLUMN_PURPLES = "COLUMN_PURPLES";
    private static final String COLUMN_PINKS = "COLUMN_PINKS";
    private static final String COLUMN_WHITES = "COLUMN_WHITES";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "ibdTrackerInfo.db", null, 1);
    }

    // Runs first time this is called
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DATE + " TEXT, " + COLUMN_ISRED + " BOOL, " + COLUMN_ISORANGE + " BOOL, " + COLUMN_ISYELLOW + " BOOL, " + COLUMN_ISGREEN + " BOOL, " + COLUMN_ISBLUE + " BOOL, " + COLUMN_ISPURPLE + " BOOL, " + COLUMN_ISPINK + " BOOL, " + COLUMN_ISWHITE + " BOOL, " + COLUMN_FORTNIGHTNUMBER + " INT, " + COLUMN_REDS + " INT, " + COLUMN_ORANGES + " INT, " + COLUMN_YELLOWS + " INT, " + COLUMN_GREENS + " INT, " + COLUMN_BLUES + " INT, " + COLUMN_PURPLES + " INT, " + COLUMN_PINKS + " INT, " + COLUMN_WHITES + " INT)";
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
        cv.put(COLUMN_ISGREEN, calendar_cell.getGreen());
        cv.put(COLUMN_ISBLUE, calendar_cell.getBlue());
        cv.put(COLUMN_ISPURPLE, calendar_cell.getPurple());
        cv.put(COLUMN_ISPINK, calendar_cell.getPink());
        cv.put(COLUMN_ISWHITE, calendar_cell.getWhite());
        cv.put(COLUMN_FORTNIGHTNUMBER, calendar_cell.getFortnightNumber());
        cv.put(COLUMN_REDS, calendar_cell.getReds());
        cv.put(COLUMN_ORANGES, calendar_cell.getOranges());
        cv.put(COLUMN_YELLOWS, calendar_cell.getYellows());
        cv.put(COLUMN_GREENS, calendar_cell.getGreens());
        cv.put(COLUMN_BLUES, calendar_cell.getBlues());
        cv.put(COLUMN_PURPLES, calendar_cell.getPurples());
        cv.put(COLUMN_PINKS, calendar_cell.getPinks());
        cv.put(COLUMN_WHITES, calendar_cell.getWhites());

        db.insert(CUSTOMER_TABLE,null,cv);
    }

    public ArrayList<Calendar_cell> getData(){
        ArrayList<Calendar_cell> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            // loop through and create new customer object for each row
            do {
                String date = cursor.getString(1);
                String[] realDate = date.toString().split("-");
                int day = Integer.valueOf(realDate[0]);
                int month = Integer.valueOf(realDate[1]);
                int year = Integer.valueOf(realDate[2]);

                boolean isRed = cursor.getInt(2) == 1 ? true: false;
                boolean isOrange = cursor.getInt(3) == 1 ? true: false;
                boolean isYellow = cursor.getInt(4) == 1 ? true: false;
                boolean isGreen = cursor.getInt(5) == 1 ? true: false;
                boolean isBlue = cursor.getInt(6) == 1 ? true: false;
                boolean isPurple = cursor.getInt(7) == 1 ? true: false;
                boolean isPink = cursor.getInt(8) == 1 ? true: false;
                boolean isWhite = cursor.getInt(9) == 1 ? true: false;
                int fortnightNumber = cursor.getInt(10);
                int reds = cursor.getInt(11);
                int oranges = cursor.getInt(12);
                int yellows = cursor.getInt(13);
                int greens = cursor.getInt(14);
                int blues = cursor.getInt(15);
                int purples = cursor.getInt(16);
                int pinks = cursor.getInt(17);
                int whites = cursor.getInt(18);

                Calendar_cell calendar_cell = new Calendar_cell(day, month, year, isRed, isOrange, isYellow, isGreen, isBlue, isPurple, isPink, isWhite, fortnightNumber, reds, oranges, yellows, greens, blues, purples, pinks, whites);
                returnList.add(calendar_cell);

            } while (cursor.moveToNext());
        } else {
            // failure
        }
        cursor.close();
        db.close();
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
        cv.put(COLUMN_ISGREEN, calendar_cell.getGreen());
        cv.put(COLUMN_ISBLUE, calendar_cell.getBlue());
        cv.put(COLUMN_ISPURPLE, calendar_cell.getPurple());
        cv.put(COLUMN_ISPINK, calendar_cell.getPink());
        cv.put(COLUMN_ISWHITE, calendar_cell.getWhite());
        cv.put(COLUMN_FORTNIGHTNUMBER, calendar_cell.getFortnightNumber());
        cv.put(COLUMN_REDS, calendar_cell.getReds());
        cv.put(COLUMN_ORANGES, calendar_cell.getOranges());
        cv.put(COLUMN_YELLOWS, calendar_cell.getYellows());
        cv.put(COLUMN_GREENS, calendar_cell.getGreens());
        cv.put(COLUMN_BLUES, calendar_cell.getBlues());
        cv.put(COLUMN_PURPLES, calendar_cell.getPurples());
        cv.put(COLUMN_PINKS, calendar_cell.getPinks());
        cv.put(COLUMN_WHITES, calendar_cell.getWhites());
        db.update(CUSTOMER_TABLE, cv, "COLUMN_DATE = ?", new String[] {date});
    }
}
