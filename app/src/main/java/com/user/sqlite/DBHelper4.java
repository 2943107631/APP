package com.user.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper4 extends SQLiteOpenHelper {

    private static final String Friend = "CREATE TABLE friend (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    username TEXT,\n" +
            "    content TEXT,\n" +
            "    time TEXT,\n" +
            "    userId INTEGER,\n" +
            "    love INTEGER\n" +
            ");";

    public DBHelper4(@Nullable Context context) {
        super(context, "friend.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Friend);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
