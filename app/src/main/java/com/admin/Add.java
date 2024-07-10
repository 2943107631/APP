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
import com.user.sqlite.DBHelper2;

public class Add extends AppCompatActivity {

    private EditText title;
    private EditText salary;
    private EditText username;
    private EditText address;
    private EditText content;
    private AppCompatButton add;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        number = getIntent().getIntExtra("number", 0);
        initView();

        add.setOnClickListener(v -> {
            DBHelper2 dbHelper2 = new DBHelper2(this);
            SQLiteDatabase db = dbHelper2.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("title", title.getText().toString());
            values.put("salary", salary.getText().toString());
            values.put("content", content.getText().toString());
            values.put("username", username.getText().toString());
            values.put("address", address.getText().toString());
            values.put("number", number);
            long insert = db.insert("job", null, values);
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
        ((TextView) toolbar.getChildAt(1)).setText("添加招聘");

        title = (EditText) findViewById(R.id.title);
        salary = (EditText) findViewById(R.id.salary);
        username = (EditText) findViewById(R.id.username);
        address = (EditText) findViewById(R.id.address);
        content = (EditText) findViewById(R.id.content);
        add = (AppCompatButton) findViewById(R.id.add);
    }
}