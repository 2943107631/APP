package com.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.user.sqlite.DBHelper1;

public class Resume extends AppCompatActivity {

    private EditText full_name;
    private EditText age;
    private RadioGroup sex;
    private RadioButton man;
    private RadioButton woman;
    private EditText height;
    private EditText bust;
    private EditText waist;
    private EditText hip;
    private EditText salary;
    private EditText post;
    private AppCompatButton confirm;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        id = getIntent().getIntExtra("id", 0);
        initView();

        // 初始化值
        setData();

        confirm.setOnClickListener(v -> {
            String Full_name = full_name.getText().toString();
            String Expected_position = post.getText().toString();
            int gender = man.isChecked() ? 0 : 1;
            int Age = Integer.parseInt(age.getText().toString());
            int Height = Integer.parseInt(height.getText().toString());
            int Bust = Integer.parseInt(bust.getText().toString());
            int Waist = Integer.parseInt(waist.getText().toString());
            int Hip = Integer.parseInt(hip.getText().toString());
            int Salary = Integer.parseInt(salary.getText().toString());

            DBHelper1 dbHelper1 = new DBHelper1(this);
            SQLiteDatabase db = dbHelper1.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("full_name", Full_name);
            values.put("expected_position", Expected_position);
            values.put("gender", gender);
            values.put("age", Age);
            values.put("height", Height);
            values.put("bust_size", Bust);
            values.put("waist_size", Waist);
            values.put("hip_size", Hip);
            values.put("expected_salary", Salary);

            int rowsUpdated = db.update("user", values, "id=?", new String[]{String.valueOf(id)});
            if (rowsUpdated > 0) {
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
            }
            dbHelper1.close();
            db.close();
        });
    }

    private void setData() {
        DBHelper1 dbHelper1 = new DBHelper1(this);
        SQLiteDatabase db = dbHelper1.getWritableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            String Full_name = cursor.getString(cursor.getColumnIndex("full_name"));
            String Expected_position = cursor.getString(cursor.getColumnIndex("expected_position"));
            int gender = cursor.getInt(cursor.getColumnIndex("gender"));
            int Age = cursor.getInt(cursor.getColumnIndex("age"));
            int Height = cursor.getInt(cursor.getColumnIndex("height"));
            int Bust = cursor.getInt(cursor.getColumnIndex("bust_size"));
            int Waist = cursor.getInt(cursor.getColumnIndex("waist_size"));
            int Hip = cursor.getInt(cursor.getColumnIndex("hip_size"));
            int Salary = cursor.getInt(cursor.getColumnIndex("expected_salary"));

            full_name.setText(Full_name);
            post.setText(Expected_position);
            age.setText(String.valueOf(Age));
            height.setText(String.valueOf(Height));
            bust.setText(String.valueOf(Bust));
            waist.setText(String.valueOf(Waist));
            hip.setText(String.valueOf(Hip));
            salary.setText(String.valueOf(Salary));
            if (gender == 0) {
                man.setChecked(true);
            } else if (gender == 1) {
                woman.setChecked(true);
            }
        }
        dbHelper1.close();
        cursor.close();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("个人简历");

        full_name = (EditText) findViewById(R.id.full_name);
        age = (EditText) findViewById(R.id.age);
        sex = (RadioGroup) findViewById(R.id.sex);
        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);
        height = (EditText) findViewById(R.id.height);
        bust = (EditText) findViewById(R.id.bust);
        waist = (EditText) findViewById(R.id.waist);
        hip = (EditText) findViewById(R.id.hip);
        salary = (EditText) findViewById(R.id.salary);
        post = (EditText) findViewById(R.id.post);
        confirm = (AppCompatButton) findViewById(R.id.confirm);
    }
}