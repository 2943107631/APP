package com.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.user.sqlite.DBHelper3;

public class Add2 extends AppCompatActivity {

    private EditText title;
    private EditText username;
    private EditText address;
    private EditText content;
    private EditText price;
    private AppCompatButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        initView();
        add.setOnClickListener(v -> {
            DBHelper3 dbHelper3 = new DBHelper3(this);
            SQLiteDatabase db = dbHelper3.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("title", title.getText().toString());
            values.put("content", content.getText().toString());
            values.put("username", username.getText().toString());
            values.put("address", address.getText().toString());
            values.put("price", price.getText().toString());
            long insert = db.insert("jipai", null, values);
            if (insert != -1) {
                finish();
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
            }
            db.close();
        });
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("添加寄拍");

        title = (EditText) findViewById(R.id.title);
        username = (EditText) findViewById(R.id.username);
        address = (EditText) findViewById(R.id.address);
        content = (EditText) findViewById(R.id.content);
        price = (EditText) findViewById(R.id.price);
        add = (AppCompatButton) findViewById(R.id.add);
    }
}