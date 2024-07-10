package com.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.admin.MainActivity3;
import com.example.R;
import com.user.sqlite.DBHelper1;

public class MainActivity extends AppCompatActivity {

    private ImageView start;
    private TextView register, forget, login;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // 进入注册账号页面
        register.setOnClickListener(v -> startActivity(new Intent(this, Register.class)));
        // 进入忘记密码页面
        forget.setOnClickListener(v -> startActivity(new Intent(this, Forget.class)));
        // 登录
        login.setOnClickListener(v -> {
            // 进入管理员账号
            if (email.getText().toString().equals("admin") && password.getText().toString().equals("123456")) {
                startActivity(new Intent(this, MainActivity3.class));
                finish();
            } else {
                // 登录进账号
                DBHelper1 dbHelper1 = new DBHelper1(this);
                SQLiteDatabase db = dbHelper1.getReadableDatabase();
                String selection = "email = ? AND password = ?";
                String[] selectionArgs = {email.getText().toString(), password.getText().toString()};
                Cursor cursor = db.query("user", null, selection, selectionArgs, null, null, null);
                if (cursor.moveToFirst()) {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity2.class);
                    intent.putExtra("id", cursor.getInt(cursor.getColumnIndex("id")));
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setVisibility(View.GONE);
        ((TextView) toolbar.getChildAt(1)).setText("登录");
        start = findViewById(R.id.start);
        //  启动页睡眠 然后隐藏图片进入登录界面
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                runOnUiThread(() -> {
                    start.setVisibility(View.GONE);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        register = findViewById(R.id.register);
        forget = findViewById(R.id.forget);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }
}