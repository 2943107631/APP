package com.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.user.bean.CommentBean;
import com.user.bean.ExChangeBean;
import com.user.sqlite.DBHelper1;
import com.user.sqlite.DBHelper4;
import com.user.sqlite.DBHelper5;
import com.user.util.AdapterUtil;
import com.user.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class DianZan extends AppCompatActivity {

    private RecyclerView recy;
    private List<ExChangeBean> list = new ArrayList<>();
    private List<CommentBean> list1 = new ArrayList<>();
    private int id;
    private String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_zan);
        id = getIntent().getIntExtra("id", 0);
        initView();
        getData();

        setRecy();
    }

    private void setRecy() {
        list.clear();
        DBHelper4 dbHelper4 = new DBHelper4(this);
        SQLiteDatabase db = dbHelper4.getWritableDatabase();
        String sqlQuery = "SELECT * FROM friend";
        Cursor cursor = db.rawQuery(sqlQuery, null);
        while (cursor.moveToNext()) {
            ExChangeBean bean = new ExChangeBean(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getInt(cursor.getColumnIndex("love"))
            );
            if (bean.getLove() == 1)
                list.add(bean);
        }
        cursor.close();
        db.close();
        dbHelper4.close();

        list1.clear();
        DBHelper5 dbHelper5 = new DBHelper5(this);
        SQLiteDatabase db5 = dbHelper5.getWritableDatabase();
        String sqlQuery1 = "SELECT * FROM comment";
        Cursor cursor1 = db5.rawQuery(sqlQuery1, null);
        while (cursor1.moveToNext()) {
            CommentBean bean = new CommentBean(
                    cursor1.getInt(cursor1.getColumnIndex("exId")),
                    cursor1.getString(cursor1.getColumnIndex("username")),
                    cursor1.getString(cursor1.getColumnIndex("content"))
            );
            list1.add(bean);
        }
        dbHelper5.close();
        cursor1.close();
        db5.close();

        AdapterUtil<ExChangeBean> adapter = new AdapterUtil<>(R.layout.exchange_item, ((data, position, holder) -> {
            ImageView image = (ImageView) holder.getView(R.id.image);
            TextView username = (TextView) holder.getView(R.id.username);
            TextView content = (TextView) holder.getView(R.id.content);
            ImageView image1 = (ImageView) holder.getView(R.id.image1);
            TextView time = (TextView) holder.getView(R.id.time);
            image.setImageResource(ImageUtil.getImage());
            image1.setImageResource(ImageUtil.getImage());
            username.setText(data.getUsername());
            content.setText(data.getContent());
            time.setText(data.getTime());

            TextView delete = holder.getView(R.id.delete);
            if (UserName.equals(data.getUsername()))
                delete.setVisibility(View.VISIBLE);
            else delete.setVisibility(View.GONE);
            delete.setOnClickListener(v -> {
                SQLiteDatabase db4 = dbHelper4.getWritableDatabase();
                String selection = "id=?";
                String[] selectionArgs = {String.valueOf(data.getId())};
                int deletedRows = db4.delete("friend", selection, selectionArgs);
                if (deletedRows != -1) {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                    setRecy();
                } else {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }
                db4.close();
            });

            ImageView xin = holder.getView(R.id.xin);
            if (data.getLove() == 0)
                xin.setImageResource(R.drawable.x1);
            else
                xin.setImageResource(R.drawable.x2);
            xin.setOnClickListener(v -> {
                SQLiteDatabase db4 = dbHelper4.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("love", data.getLove() == 0 ? 1 : 0);
                int rowsUpdated = db4.update("friend", values, "id=?", new String[]{String.valueOf(data.getId())});
                if (rowsUpdated > 0) {
                    setRecy();
                }
                db.close();
            });

            List<CommentBean> list2 = new ArrayList<>();
            for (CommentBean commentBean : list1) {
                if (commentBean.getExId() == data.getId())
                    list2.add(commentBean);
            }
            RecyclerView comment = holder.getView(R.id.comment);
            comment.setLayoutManager(new LinearLayoutManager(this));
            AdapterUtil<CommentBean> adapter1 = new AdapterUtil<>(R.layout.comment_item, ((data1, position1, holder1) -> {
                TextView text = holder1.getView(R.id.text);
                text.setText(data1.getUsername() + " : " + data1.getContent());
            }));
            adapter1.listUpdate(list2);
            comment.setAdapter(adapter1);

            EditText edit = holder.getView(R.id.edit);
            Button btn = holder.getView(R.id.btn);
            btn.setOnClickListener(v -> {
                SQLiteDatabase db1 = dbHelper5.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("username", UserName);
                values.put("content", edit.getText().toString());
                values.put("exId", data.getId());
                long insert = db1.insert("comment", null, values);
                if (insert != -1) {
                    Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
                    edit.setText("");
                    setRecy();
                } else {
                    Toast.makeText(this, "发送失败", Toast.LENGTH_SHORT).show();
                }
                db1.close();
            });
        }));
        adapter.listUpdate(list);
        recy.setAdapter(adapter);
    }

    private void getData() {
        DBHelper1 dbHelper = new DBHelper1(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            UserName = cursor.getString(cursor.getColumnIndex("username"));
        }
        dbHelper.close();
        cursor.close();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("我的点赞");
        recy = findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));
    }
}