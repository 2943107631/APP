package com.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.user.sqlite.DBHelper1;
import com.user.sqlite.DBHelper6;
import com.user.util.DateUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddMoCha extends AppCompatActivity {

    private EditText content;
    private AppCompatButton add;
    private ImageView image;

    private static final int REQUEST_SELECT_IMAGE = 100;
    private String imagePath;
    private int id;
    private String username;

    // 启动相册应用选择图片
    private void selectImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_SELECT_IMAGE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mo_cha);
        id = getIntent().getIntExtra("id", 0);
        initView();
        getData();

        image.setOnClickListener(v -> {
            selectImageFromGallery();
        });

        add.setOnClickListener(v -> {
            addImageToDatabase();
        });
    }

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
        ((TextView) toolbar.getChildAt(1)).setText("发布摩卡/视频");
        content = findViewById(R.id.content);
        add = findViewById(R.id.add);
        image = findViewById(R.id.image);
    }

    // 将图片二进制数据添加到数据库
    private void addImageToDatabase() {
        DBHelper6 dbHelper6 = new DBHelper6(this);
        SQLiteDatabase db = dbHelper6.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("image", imagePath);
        values.put("content", content.getText().toString());
        values.put("username", username);
        values.put("userId", id);
        long insert = db.insert("mocha", null, values);
        if (insert != -1) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
        db.close();
        dbHelper6.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_SELECT_IMAGE) {
                if (data != null) {
                    imagePath=data.getData().toString();
                    image.setImageURI(Uri.parse(imagePath));
                }
            }
        }
    }
}