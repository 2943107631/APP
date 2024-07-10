package com.user.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper5 extends SQLiteOpenHelper {

    private static final String Comment = "CREATE TABLE comment (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    username TEXT,\n" +
            "    content TEXT,\n" +
            "    exId INTEGER\n" +
            ");";

    public DBHelper5(@Nullable Context context) {
        super(context, "comment.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Comment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
