package com.user.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.admin.Add2;
import com.example.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.user.activity.AddExChange;
import com.user.bean.CommentBean;
import com.user.bean.ExChangeBean;
import com.user.bean.HomeModelBean;
import com.user.sqlite.DBHelper1;
import com.user.sqlite.DBHelper2;
import com.user.sqlite.DBHelper4;
import com.user.sqlite.DBHelper5;
import com.user.util.AdapterUtil;
import com.user.util.DateUtil;
import com.user.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class ExChangeFragment extends Fragment {

    private RecyclerView recy;
    private FloatingActionButton fab;
    private List<ExChangeBean> list = new ArrayList<>();
    private List<CommentBean> list1 = new ArrayList<>();
    private int id;
    private String UserName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex_change, container, false);
        // 获取用户id
        id = getActivity().getIntent().getIntExtra("id", 0);
        initView(view);
        getData();

        // 设置子列表内容
        setRecy();

        // 添加帖子
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddExChange.class);
            intent.putExtra("id", id);
            getActivity().startActivity(intent);
        });
        return view;
    }

    // 设置子列表内容
    private void setRecy() {
        list.clear();
        DBHelper4 dbHelper4 = new DBHelper4(getContext());
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
            list.add(bean);
        }
        cursor.close();
        db.close();
        dbHelper4.close();

        list1.clear();
        DBHelper5 dbHelper5 = new DBHelper5(getContext());
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
            if (UserName.equals(data.getUsername())) delete.setVisibility(View.VISIBLE);
            else delete.setVisibility(View.GONE);
            delete.setOnClickListener(v -> {
                SQLiteDatabase db4 = dbHelper4.getWritableDatabase();
                String selection = "id=?";
                String[] selectionArgs = {String.valueOf(data.getId())};
                int deletedRows = db4.delete("friend", selection, selectionArgs);
                if (deletedRows != -1) {
                    Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    setRecy();
                } else {
                    Toast.makeText(getContext(), "删除失败", Toast.LENGTH_SHORT).show();
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
            comment.setLayoutManager(new LinearLayoutManager(getContext()));
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
                    Toast.makeText(getContext(), "发送成功", Toast.LENGTH_SHORT).show();
                    edit.setText("");
                    setRecy();
                } else {
                    Toast.makeText(getContext(), "发送失败", Toast.LENGTH_SHORT).show();
                }
                db1.close();
            });
        }));
        adapter.listUpdate(list);
        recy.setAdapter(adapter);
    }

    // 获取用户名
    private void getData() {
        DBHelper1 dbHelper = new DBHelper1(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            UserName = cursor.getString(cursor.getColumnIndex("username"));
        }
        dbHelper.close();
        cursor.close();
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setVisibility(View.GONE);
        ((TextView) toolbar.getChildAt(1)).setText("互动交流区");
        recy = view.findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        fab = view.findViewById(R.id.fab);
    }

    @Override
    public void onResume() {
        super.onResume();
        setRecy();
    }
}