package com.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.user.sqlite.DBHelper1;

public class Personal extends AppCompatActivity {

    private RelativeLayout setImage;
    private ImageView myImage;
    private EditText username;
    private EditText phone;
    private EditText email;
    private AppCompatButton confirm;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        id = getIntent().getIntExtra("id", 0);
        initView();

        // 初始化值
        setData();

        confirm.setOnClickListener(v -> {
            String updatedUserName = username.getText().toString();
            String updatedPhoneNumber = phone.getText().toString();
            String updatedEmail = email.getText().toString();

            DBHelper1 dbHelper1 = new DBHelper1(this);
            SQLiteDatabase db = dbHelper1.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("username", updatedUserName);
            values.put("phone_number", updatedPhoneNumber);
            values.put("email", updatedEmail);

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
            String userName = cursor.getString(cursor.getColumnIndex("username"));
            String phoneNumber = cursor.getString(cursor.getColumnIndex("phone_number"));
            String Email = cursor.getString(cursor.getColumnIndex("email"));
            username.setText(userName);
            phone.setText(phoneNumber);
            email.setText(Email);
        }
        dbHelper1.close();
        cursor.close();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("用户信息");

        setImage = (RelativeLayout) findViewById(R.id.setImage);
        myImage = (ImageView) findViewById(R.id.myImage);
        username = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        confirm = (AppCompatButton) findViewById(R.id.confirm);
    }
}