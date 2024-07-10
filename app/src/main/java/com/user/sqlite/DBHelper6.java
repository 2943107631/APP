package com.user.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper6 extends SQLiteOpenHelper {

    private static final String Mocha = "CREATE TABLE mocha (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    image TEXT,\n" +
            "    content TEXT,\n" +
            "    username TEXT,\n" +
            "    userId INTEGER\n" +
            ");";

    public DBHelper6(@Nullable Context context) {
        super(context, "mocha.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Mocha);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
