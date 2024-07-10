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

public class Forget extends AppCompatActivity {

    private AppCompatEditText email;
    private AppCompatEditText password;
    private AppCompatEditText passwordd;
    private AppCompatButton forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();

        // 忘记密码
        forget.setOnClickListener(v -> {
            if (!password.getText().toString().equals(passwordd.getText().toString())) {
                Toast.makeText(this, "两次密码不正确", Toast.LENGTH_SHORT).show();
            } else if (email.getText().toString().isEmpty()) {
                Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            } else if (password.getText().toString().isEmpty()) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            } else {
                DBHelper1 dbHelper1 = new DBHelper1(this);
                SQLiteDatabase db = dbHelper1.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("password", password.getText().toString());
                int rowsUpdated = db.update("user", values, "email=?", new String[]{email.getText().toString()});
                if (rowsUpdated != -1) {
                    finish();
                    Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "密码修改失败", Toast.LENGTH_SHORT).show();
                }
                dbHelper1.close();
                db.close();
            }
        });
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("忘记密码");
        email = (AppCompatEditText) findViewById(R.id.email);
        password = (AppCompatEditText) findViewById(R.id.password);
        passwordd = (AppCompatEditText) findViewById(R.id.passwordd);
        forget = (AppCompatButton) findViewById(R.id.forget);
    }
}