package com.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.user.sqlite.DBHelper1;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {

    private AppCompatEditText username;
    private AppCompatEditText email;
    private AppCompatEditText phone;
    private AppCompatEditText password;
    private AppCompatEditText passwordd;
    private AppCompatButton register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        // 注册账号
        register.setOnClickListener(v -> {
            if (!password.getText().toString().equals(passwordd.getText().toString())) {
                Toast.makeText(this, "两次密码不正确", Toast.LENGTH_SHORT).show();
            } else if (email.getText().toString().isEmpty()) {
                Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            } else if (username.getText().toString().isEmpty()) {
                Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            } else if (phone.getText().toString().isEmpty()) {
                Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            } else if (password.getText().toString().isEmpty()) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            } else {
                DBHelper1 dbHelper1 = new DBHelper1(this);
                SQLiteDatabase db = dbHelper1.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("username", username.getText().toString());
                values.put("email", email.getText().toString());
                values.put("phone_number", phone.getText().toString());
                values.put("password", password.getText().toString());
                long insertResult = db.insert("user", null, values);
                if (insertResult != -1) {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("注册账号");

        username = (AppCompatEditText) findViewById(R.id.username);
        email = (AppCompatEditText) findViewById(R.id.email);
        phone = (AppCompatEditText) findViewById(R.id.phone);
        password = (AppCompatEditText) findViewById(R.id.password);
        passwordd = (AppCompatEditText) findViewById(R.id.passwordd);
        register = (AppCompatButton) findViewById(R.id.register);
    }
}