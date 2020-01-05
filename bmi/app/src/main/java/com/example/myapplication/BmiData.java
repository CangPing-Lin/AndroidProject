package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BmiData extends SQLiteOpenHelper {
    private final static String DB="DB.db";
    private final static String TB="TB";
    private final static int VS=2;

    public BmiData(Context context) {
        //super(context, name, factory, version);
        super(context,DB, null, VS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL="CREATE TABLE IF NOT EXISTS "+TB+"(_id INTEGER PRIMARY KEY AUTOINCREMENT ,_title VARCHAR(50))";
        sqLiteDatabase.execSQL(SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String SQL=TB;
        sqLiteDatabase.execSQL(SQL);
    }
}