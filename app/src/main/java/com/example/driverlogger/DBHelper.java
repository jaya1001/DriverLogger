package com.example.driverlogger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyData.db";
    private static final String LOG_TABLE_NAME = "entries";
    private static final String DRIVER_TABLE_NAME = "driver";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_VEHICLE = "vehicle";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_REGO = "rego";
    private static final String COLUMN_START = "start";
    private static final String COLUMN_FIRST = "first_brk";
    private static final String COLUMN_SECOND = "sec_brk";
    private static final String COLUMN_END = "end";


    private static final String query =  "CREATE TABLE " + LOG_TABLE_NAME + "(" + COLUMN_VEHICLE + " TEXT," + COLUMN_NAME + " TEXT," + COLUMN_REGO + " TEXT," +
            COLUMN_START + " TEXT," + COLUMN_FIRST + " TEXT," + COLUMN_SECOND + " TEXT," + COLUMN_END + " TEXT)";

    private static final String query1 = "CREATE TABLE " + DRIVER_TABLE_NAME + "(" + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " +  DRIVER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +  LOG_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDriver(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        db.insert(DRIVER_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertLog(String vehicle, String name, String rego, String start, String first, String second, String end)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_VEHICLE, vehicle);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_REGO, rego);
        contentValues.put(COLUMN_START, start);
        contentValues.put(COLUMN_FIRST, first);
        contentValues.put(COLUMN_SECOND, second);
        contentValues.put(COLUMN_END, end);
        db.insert(LOG_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(String vehicle) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = null;
        String[] projection = {
                COLUMN_NAME,
                COLUMN_REGO,
                COLUMN_START,
                COLUMN_FIRST,
                COLUMN_SECOND,
                COLUMN_END
        };

        String selection = COLUMN_VEHICLE + " = ?";
        String[] selectionArgs = { vehicle };
        res = db.query(
                LOG_TABLE_NAME, projection, selection, selectionArgs,null,null,null
        );
        return res;
    }

   // public int numberOfRowsInCar(){
        //SQLiteDatabase db = this.getReadableDatabase();
        //int numRows = (int) DatabaseUtils.queryNumEntries(db, IMAGES_TABLE_NAME);
      //  return numRows;
    //}

    public int numberOfDrivers(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DRIVER_TABLE_NAME);
        return numRows;
    }

    public void deleteAll(String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ table);
    }
}