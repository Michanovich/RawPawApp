package com.viktor.listtest;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

public class DBAdapter {
    private static final String TAG = "DataAdapter";

    private final Context context;
    public SQLiteDatabase database;
    private DBHelper dbHelper;

    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public DBAdapter createDatabase() throws SQLException {
        try {
            dbHelper.createDatabase();
        } catch (IOException e) {
            Log.e(TAG, e.toString() + "Unable to create database");
            throw new Error(("Unable to create database"));
        }
        return this;
    }

    public DBAdapter open(String readableOrWriteable) throws SQLException {
        try {
            dbHelper.openDatabase();
//            dbHelper.close();
            if(readableOrWriteable.equals("readable")){
                database = dbHelper.getReadableDatabase();
            } else if (readableOrWriteable.equals("writable")){
                database = dbHelper.getWritableDatabase();
            }
        } catch (SQLException e) {
            Log.e(TAG, "open >> " + e.toString());
            throw e;
        }
        return this;
    }

    public DBAdapter open() throws SQLException {
        try {
            dbHelper.openDatabase();
            database = dbHelper.getWritableDatabase();
        } catch (SQLException e){
            Log.e(TAG, "open >> " + e.toString());
            throw e;
        }
        return this;
    }

    public void close(){
        dbHelper.close();
    }
}
