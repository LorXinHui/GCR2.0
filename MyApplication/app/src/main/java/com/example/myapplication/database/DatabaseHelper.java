package com.example.myapplication.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.items.CommunityItem;
import com.example.myapplication.items.NewsItem;
import com.example.myapplication.object.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "jobspark.db";
    private static final int DATABASE_VERSION = 1;
    private final Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;

        /*
        try {
            copyDatabase(); // Call copyDatabase method in the constructor
        } catch (IOException e) {
            Log.e(TAG, "Error copying database", e);
        }
         */
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
            user.setUser_status(userStatus);
            user.setUser_position(userPosition);
            user.setUser_email(userEmail);
            user.setUser_contact(userContact);
        }
        cursor.close(); // Close the cursor when finished

        return user;
    }

    public String getUniversity(int user_id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select uni_name from university where uni_id = (select uni_id from university_record where user_id = ?)", new String[]{String.valueOf(user_id)});

        String university = null;
        if (cursor.moveToFirst()){
            university = cursor.getString(0);
        }
        cursor.close();
        return university;
    }

    public String getMajor(int user_id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select major_name from university_major where major_id = (select major_id from university_record where user_id = ?)", new String[]{String.valueOf(user_id)});

        String major = null;
        if (cursor.moveToFirst()){
            major = cursor.getString(0);
        }
        cursor.close();
        return major;
    }

    public ArrayList<CommunityItem> getCommunity(){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from community", null);

        // create new array list
        ArrayList<CommunityItem> communityList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do{
                communityList.add(new CommunityItem(
                    cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2))
                );
            } while (cursor.moveToNext());
            // retrieve data from the cursor


        }
        cursor.close();
        return communityList;
    }

    public ArrayList<String> getCert(int user_id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select cert_name from certificate where cert_id IN (select cert_id from certificate_record where user_id = ?)", new String[]{String.valueOf(user_id)});

        ArrayList<String> certs = new ArrayList<>();
        while (cursor.moveToNext()) {
            certs.add(cursor.getString(0));
        }

        cursor.close();
        return certs;
    }

    public ArrayList<String> getSkill(ArrayList<String> user_cert){
        SQLiteDatabase myDB = this.getReadableDatabase();
        //Cursor cursor = myDB.rawQuery("select skills_name from skills where skills_id IN (select skills_id from skills_achieved where cert_id IN ?)", user_cert.toArray(new String[0]));

        ArrayList<String> skills = new ArrayList<>();
        for(String cert: user_cert){
            Cursor cursor = myDB.rawQuery("select skills_name from skills where skills_id IN (select skills_id from skills_achieved where cert_id = (select cert_id from certificate where cert_name = ?))", new String[]{cert});

            while (cursor.moveToNext()){
                skills.add(cursor.getString(0));
            }
            cursor.close();
        }

        return skills;
    }

    public ArrayList<CommunityItem> getCommunityJoined(int user_id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from community where comm_id IN (select comm_id from community_record where user_id = ?)", new String[]{String.valueOf(user_id)});

        // list of communities joined
        ArrayList<CommunityItem> communityJoined = new ArrayList<>();
        while (cursor.moveToNext()) {
            communityJoined.add(new CommunityItem(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            ));
        }
        cursor.close();
        return communityJoined;
    }


    public ArrayList<NewsItem> getPost(String comm_name){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from community_post where comm_id = (select comm_id from community where comm_name = ?)", new String[]{comm_name});

        // create new array list
        ArrayList<NewsItem> newsList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do{
                @SuppressLint("Range")
                int postId = cursor.getInt(cursor.getColumnIndex("post_id")); // Adjust column index as needed
                @SuppressLint("Range")
                int userId = cursor.getInt(cursor.getColumnIndex("user_id"));
                @SuppressLint("Range")
                String commDate = cursor.getString(cursor.getColumnIndex("comm_date"));
                @SuppressLint("Range")
                String commTitle = cursor.getString(cursor.getColumnIndex("comm_title"));
                @SuppressLint("Range")
                String commType = cursor.getString(cursor.getColumnIndex("comm_type"));
                @SuppressLint("Range")
                String commContent = cursor.getString(cursor.getColumnIndex("comm_content"));

                NewsItem post = new NewsItem(commTitle, commType, commContent);
                post.setPost_id(postId);
                post.setUser_id(userId);
                post.setComm_date(commDate);

                newsList.add(post);
            } while (cursor.moveToNext());
            // retrieve data from the cursor


        }
        cursor.close();
        return newsList;
    }
}

