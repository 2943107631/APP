package com.user.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper1 extends SQLiteOpenHelper {

    private static final String User = "CREATE TABLE user (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    username TEXT,\n" +
            "    email TEXT UNIQUE,\n" +
            "    phone_number TEXT,\n" +
            "    password TEXT,\n" +
            "    avatar BLOB,\n" +
            "    gender INTEGER,\n" +
            "    full_name TEXT,\n" +
            "    age INTEGER,\n" +
            "    height INTEGER,\n" +
            "    bust_size INTEGER,\n" +
            "    waist_size INTEGER,\n" +
            "    hip_size INTEGER,\n" +
            "    expected_salary INTEGER,\n" +
            "    expected_position TEXT\n" +
            ");";

    public DBHelper1(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
