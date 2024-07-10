package com.user.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.user.activity.AddExChange;
import com.user.activity.AddMoCha;
import com.user.bean.ExChangeBean;
import com.user.bean.MoChaBean;
import com.user.sqlite.DBHelper1;
import com.user.sqlite.DBHelper4;
import com.user.sqlite.DBHelper6;
import com.user.util.AdapterUtil;
import com.user.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class MoChaFragment extends Fragment {

    private RecyclerView recy;
    private FloatingActionButton fab;
    private int id;
    private List<MoChaBean> list = new ArrayList<>();
    private String UserName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mo_cha, container, false);
        id = getActivity().getIntent().getIntExtra("id", 0);
        initView(view);
        setRecy();
        getData();

        // 点击添加按钮 进入添加内容界面
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddMoCha.class);
            intent.putExtra("id", id);
            getActivity().startActivity(intent);
        });
        return view;
    }

    // 设置子列表内容
    private void setRecy() {
        list.clear();
        DBHelper6 dbHelper6 = new DBHelper6(getContext());
        SQLiteDatabase db = dbHelper6.getReadableDatabase();
        String sqlQuery = "SELECT * FROM mocha";
        Cursor cursor = db.rawQuery(sqlQuery, null);
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
            Glide.with(getContext()).load(data.getImage()).into(image);

            if (UserName.equals(data.getUsername())) delete.setVisibility(View.VISIBLE);
            else delete.setVisibility(View.GONE);
            delete.setOnClickListener(v -> {
                SQLiteDatabase db6 = dbHelper6.getWritableDatabase();
                String selection = "id=?";
                String[] selectionArgs = {String.valueOf(data.getId())};
                int deletedRows = db6.delete("mocha", selection, selectionArgs);
                if (deletedRows != -1) {
                    Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    setRecy();
                } else {
                    Toast.makeText(getContext(), "删除失败", Toast.LENGTH_SHORT).show();
                }
                db6.close();
            });

        }));
        adapter.listUpdate(list);
        recy.setAdapter(adapter);
    }

    // 获取内容
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
        ((TextView) toolbar.getChildAt(1)).setText("摩卡/视频");
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