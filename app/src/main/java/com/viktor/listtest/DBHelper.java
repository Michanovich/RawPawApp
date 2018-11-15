package com.viktor.listtest;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    private static String TAG = "DBHelper";
    private static String DB_PATH = "";
    private static String DB_NAME = "RawPawDB.db";
    private SQLiteDatabase database;
    private final Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";

        this.context = context;
    }

    public void createDatabase() throws IOException{
        if(!checkDatabase()){
            this.getReadableDatabase();
            this.close();

            try {
                copyDatabase();
                Log.e(TAG, "database created");
            } catch (IOException e){
                Log.e(TAG, e.getLocalizedMessage());
            }
        }
    }

    private boolean checkDatabase(){
        File databaseFile = new File(DB_PATH + DB_NAME);
        return databaseFile.exists();
    }

    private void copyDatabase() throws IOException{
        InputStream inputStream = context.getAssets().open(DB_NAME);
        OutputStream outputStream = new FileOutputStream(DB_PATH + DB_NAME);

        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0){
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public boolean openDatabase() throws SQLException{
        String path = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return database != null;
    }

    @Override
    public synchronized void close(){
        if(database != null){
            database.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
}
