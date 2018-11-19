package com.viktor.listtest.DBModels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Spinner;
import android.widget.Toast;

import com.viktor.listtest.DBAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Dog {
    private String dog_id;
    private String dog_name;
    private double dog_idealweight;
    private String dog_activitylevel;
    private String dog_age;
    private double dog_foodmenu;
    private String dog_massunit;

    public String getDog_id() {
        return dog_id;
    }

    public void setDog_id(String dog_id) {
        this.dog_id = dog_id;
    }

    public String getDog_name() {
        return dog_name;
    }

    public void setDog_name(String dog_name) {
        this.dog_name = dog_name;
    }

    public double getDog_idealweight() {
        return dog_idealweight;
    }

    public void setDog_idealweight(double dog_idealweight) {
        this.dog_idealweight = dog_idealweight;
    }

    public String getDog_activitylevel() {
        return dog_activitylevel;
    }

    public void setDog_activitylevel(String dog_activitylevel) {
        this.dog_activitylevel = dog_activitylevel;
    }

    public String getDog_age() {
        return dog_age;
    }

    public void setDog_age(String dog_age) {
        this.dog_age = dog_age;
    }

    public double getDog_foodmenu() {
        return dog_foodmenu;
    }

    public void setDog_foodmenu(double dog_foodmenu) {
        this.dog_foodmenu = dog_foodmenu;
    }

    public String getDog_massunit() {
        return dog_massunit;
    }

    public void setDog_massunit(String dog_massunit) {
        this.dog_massunit = dog_massunit;
    }


    /*------------------------- STATICKE METODE ZA CRUD -------------------------*/
    public static ArrayList<Dog> getAllDogNames(Context context) {
        DBAdapter dbAdapter = new DBAdapter(context);
        dbAdapter.createDatabase();
        dbAdapter.open("readable");

        ArrayList<Dog> arrayList = new ArrayList<>();

        Cursor cursor = dbAdapter.database.rawQuery("SELECT * FROM dog", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Dog dog = new Dog();
            dog.setDog_id(cursor.getString(cursor.getColumnIndex("dog_id")));
            dog.setDog_name(cursor.getString(cursor.getColumnIndex("dog_name")));
            dog.setDog_idealweight(cursor.getDouble(cursor.getColumnIndex("dog_idealweight")));
            dog.setDog_activitylevel(cursor.getString(cursor.getColumnIndex("dog_activitylevel")));
            dog.setDog_age(cursor.getString(cursor.getColumnIndex("dog_age")));
            dog.setDog_foodmenu(cursor.getDouble(cursor.getColumnIndex("dog_foodmenu")));
            dog.setDog_massunit(cursor.getString(cursor.getColumnIndex("dog_massunit")));

            arrayList.add(dog);

            cursor.moveToNext();
        }
        cursor.close();
        dbAdapter.close();

        return arrayList;
    }

    public static ArrayList<Dog> getDogById(Context context, String dog_id) {
        DBAdapter dbAdapter = new DBAdapter(context);
        dbAdapter.createDatabase();
        dbAdapter.open("readable");

        ArrayList<Dog> arrayList = new ArrayList<>();

        Cursor cursor = dbAdapter.database.rawQuery("SELECT * FROM dog Where dog_id = ?", new String[]{dog_id});
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Dog dog = new Dog();
            dog.setDog_id(cursor.getString(cursor.getColumnIndex("dog_id")));
            dog.setDog_name(cursor.getString(cursor.getColumnIndex("dog_name")));

            arrayList.add(dog);

            cursor.moveToNext();
        }
        cursor.close();
        dbAdapter.close();

        return arrayList;
    }

    public static void insertIntoDog(Context context, String dog_name, double dog_idealweight, String dog_activitylevel, String dog_age, double dog_foodmenu, String dog_massunit) {
        DBAdapter dbAdapter = new DBAdapter(context);
        dbAdapter.createDatabase();
        dbAdapter.open("writable");

        SQLiteDatabase db = dbAdapter.database;
        ContentValues contentValues = new ContentValues();

        contentValues.put("dog_id", UUID.randomUUID().toString());
        contentValues.put("dog_name", dog_name);
        contentValues.put("dog_idealweight", dog_idealweight);
        contentValues.put("dog_activitylevel", dog_activitylevel);
        contentValues.put("dog_age", dog_age);
        contentValues.put("dog_foodmenu", dog_foodmenu);
        contentValues.put("dog_massunit", dog_massunit);

        try {
            long test = db.insertOrThrow("dog", null, contentValues);
        } catch (SQLException e){
            int a = 3;
        }

        dbAdapter.close();
    }

    public static void deleteDog(Context context, String dog_id) {
        DBAdapter dbAdapter = new DBAdapter(context);
        dbAdapter.createDatabase();
        dbAdapter.open("writable");

        SQLiteDatabase db = dbAdapter.database;

        db.delete("dog", "dog_id=?", new String[]{dog_id});
        dbAdapter.close();
    }

    public static void updateDog(Context context, Dog dog) {
        DBAdapter dbAdapter = new DBAdapter(context);
        dbAdapter.createDatabase();
        dbAdapter.open("writable");

        SQLiteDatabase db = dbAdapter.database;
        ContentValues contentValues = new ContentValues();

        contentValues.put("dog_name", dog.dog_name);
        contentValues.put("dog_idealweight", dog.dog_idealweight);
        contentValues.put("dog_activitylevel", dog.dog_activitylevel);
        contentValues.put("dog_age", dog.dog_age);
        contentValues.put("dog_foodmenu", dog.dog_foodmenu);

        db.update("dog", contentValues, "dog_id=?", new String[]{dog.dog_id});
        dbAdapter.close();
    }
}
