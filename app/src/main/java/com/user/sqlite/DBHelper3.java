package com.user.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper3 extends SQLiteOpenHelper {

    private static final String JiPai = "CREATE TABLE jipai (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    title TEXT,\n" +
            "    content TEXT,\n" +
            "    image BLOB,\n" + // 添加图片字段
            "    username TEXT,\n" +
            "    price TEXT,\n" +
            "    address TEXT\n" +
            ");";

    public DBHelper3(@Nullable Context context) {
        super(context, "jipai.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(JiPai);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
