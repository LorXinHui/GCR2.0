package com.example.myapplication.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.object.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "jobspark.db";
    private static final int DATABASE_VERSION = 1;
    private final Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;

        try {
            copyDatabase(); // Call copyDatabase method in the constructor
        } catch (IOException e) {
            Log.e(TAG, "Error copying database", e);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your tables here if needed
        db.execSQL("create table if not exists login (username TEXT primary key, password TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades here if needed
    }

    public void copyDatabase() throws IOException {
        InputStream inputStream = mContext.getAssets().open(DATABASE_NAME);
        String outFileName = mContext.getDatabasePath(DATABASE_NAME).getPath();
        OutputStream outputStream = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void openDatabase() throws IOException {
        if (!isDatabaseExists()) {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                Log.e(TAG, "Error copying database", e);
            }
        }
    }

    private boolean isDatabaseExists() {
        String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();
        return SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY) != null;
    }

    public boolean insertData(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("login", null, contentValues);
        myDB.close();
        // if insert successfully
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUsername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from login where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else return false;
    }

    public boolean checkUser(String username, String pwd){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from login where username = ? and password=?", new String[]{username, pwd});
        if (cursor.getCount() > 0)
            return true;
        else return false;
    }

    public User getUser(String username){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from user where user_fname = ?", new String[]{username});
        User user = null;

        if (cursor.moveToFirst()) { // Move cursor to the first row
            // Retrieve data from the cursor
            @SuppressLint("Range")
            int userId = cursor.getInt(cursor.getColumnIndex("user_id")); // Adjust column index as needed
            @SuppressLint("Range")
            String userFname = cursor.getString(cursor.getColumnIndex("user_fname"));
            @SuppressLint("Range")
            String userLname = cursor.getString(cursor.getColumnIndex("user_lname"));
            @SuppressLint("Range")
            String userStatus = cursor.getString(cursor.getColumnIndex("user_status"));
            @SuppressLint("Range")
            String userPosition = cursor.getString(cursor.getColumnIndex("user_position"));
            @SuppressLint("Range")
            String userEmail = cursor.getString(cursor.getColumnIndex("user_email"));
            @SuppressLint("Range")
            String userContact =  cursor.getString(cursor.getColumnIndex("user_contact"));
            @SuppressLint("Range")
            String userName = cursor.getString(cursor.getColumnIndex("username"));

            // Create User object with retrieved data
            user = new User(userId, userFname, userLname, userName);
        }
        cursor.close(); // Close the cursor when finished

        return user;
    }
}

