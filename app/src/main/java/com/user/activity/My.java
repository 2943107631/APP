package com.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.R;
import com.user.bean.MoChaBean;
import com.user.sqlite.DBHelper6;
import com.user.util.AdapterUtil;
import com.user.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class My extends AppCompatActivity {

    private RecyclerView recy;
    private int id;
    private List<MoChaBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        id = getIntent().getIntExtra("id", 0);
        initView();

        setRecy();
    }

    private void setRecy() {
        list.clear();
        DBHelper6 dbHelper6 = new DBHelper6(this);
        SQLiteDatabase db = dbHelper6.getReadableDatabase();
        String sqlQuery = "SELECT * FROM mocha WHERE userId=?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {
            MoChaBean bean = new MoChaBean(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("image")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getInt(cursor.getColumnIndex("userId"))
            );
            list.add(bean);
        }
        cursor.close();
        db.close();
        dbHelper6.close();

        AdapterUtil<MoChaBean> adapter = new AdapterUtil<>(R.layout.mocha_item, ((data, position, holder) -> {
            ImageView touxiang = (ImageView) holder.getView(R.id.touxiang);
            TextView username = (TextView) holder.getView(R.id.username);
            TextView content = (TextView) holder.getView(R.id.content);
            TextView delete = (TextView) holder.getView(R.id.delete);
            ImageView image = (ImageView) holder.getView(R.id.image);

            touxiang.setImageResource(ImageUtil.getImage());
            username.setText(data.getUsername());
            content.setText(data.getContent());
            Glide.with(this).load(data.getImage()).into(image);

            delete.setOnClickListener(v -> {
                SQLiteDatabase db6 = dbHelper6.getWritableDatabase();
                String selection = "id=?";
                String[] selectionArgs = {String.valueOf(data.getId())};
                int deletedRows = db6.delete("mocha", selection, selectionArgs);
                if (deletedRows != -1) {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                    setRecy();
                } else {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }
                db6.close();
            });

        }));
        adapter.listUpdate(list);
        recy.setAdapter(adapter);
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("我的作品");
        recy = findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));
    }
}