package com.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.user.sqlite.DBHelper1;
import com.google.android.material.textfield.TextInputEditText;

public class Password extends AppCompatActivity {

    private int id;
    private TextInputEditText oldPassword;
    private TextInputEditText newPassword;
    private TextInputEditText password;
    private AppCompatButton confirm;
    private String mima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        id = getIntent().getIntExtra("id", 0);
        initView();

        getData();

        confirm.setOnClickListener(v -> {
            String enteredOldPassword = oldPassword.getText().toString();
            String enteredNewPassword = newPassword.getText().toString();
            String enteredConfirmPassword = password.getText().toString();

            if (enteredOldPassword.equals(mima) && enteredNewPassword.equals(enteredConfirmPassword)) {
                DBHelper1 dbHelper1 = new DBHelper1(this);
                SQLiteDatabase db = dbHelper1.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("password", enteredNewPassword);
                int rowsUpdated = db.update("user", values, "id=?", new String[]{String.valueOf(id)});
                if (rowsUpdated > 0) {
                    finish();
                    Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "密码修改失败", Toast.LENGTH_SHORT).show();
                }
                dbHelper1.close();
                db.close();
            } else {
                Toast.makeText(this, "密码验证失败，请重新输入", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        DBHelper1 dbHelper1 = new DBHelper1(this);
        SQLiteDatabase db = dbHelper1.getReadableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            mima = cursor.getString(cursor.getColumnIndex("password"));
        }
        dbHelper1.close();
        cursor.close();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("修改密码");

        oldPassword = (TextInputEditText) findViewById(R.id.oldPassword);
        newPassword = (TextInputEditText) findViewById(R.id.newPassword);
        password = (TextInputEditText) findViewById(R.id.password);
        confirm = (AppCompatButton) findViewById(R.id.confirm);
    }
}