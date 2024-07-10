package com.user.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.example.R;
import com.user.sqlite.DBHelper1;
import com.user.sqlite.DBHelper4;
import com.user.util.DateUtil;

public class AddExChange extends AppCompatActivity {

    private EditText content;
    private AppCompatButton add;
    private int id;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ex_change);
        id = getIntent().getIntExtra("id", 0);
        initView();
        getData();

        // 添加内容
        add.setOnClickListener(v -> {
            DBHelper4 dbHelper4 = new DBHelper4(this);
            SQLiteDatabase db = dbHelper4.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("username", username);
            values.put("content", content.getText().toString());
            values.put("userId", id);
            values.put("time", DateUtil.getDate());
            values.put("love", 0);
            long insert = db.insert("friend", null, values);
            if (insert != -1) {
                finish();
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
            }
            db.close();
        });
    }
    // 获取自己的数据
    private void getData() {
        DBHelper1 dbHelper1 = new DBHelper1(this);
        SQLiteDatabase db = dbHelper1.getWritableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex("username"));
        }
        dbHelper1.close();
        cursor.close();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("发布话题");
        content = (EditText) findViewById(R.id.content);
        add = (AppCompatButton) findViewById(R.id.add);
    }
}