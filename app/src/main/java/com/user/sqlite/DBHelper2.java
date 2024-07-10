package com.user.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {

    private static final String Job = "CREATE TABLE job (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    title TEXT,\n" +
            "    salary TEXT,\n" +
            "    content TEXT,\n" +
            "    username TEXT,\n" +
            "    address TEXT,\n" +
            "    number INTEGER\n" +
            ");";

    public DBHelper2(@Nullable Context context) {
        super(context, "job.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Job);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
