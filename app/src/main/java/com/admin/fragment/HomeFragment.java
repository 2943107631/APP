package com.admin.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.Add;
import com.admin.Add2;
import com.admin.HomeDetail;
import com.admin.JiPaiDetail;
import com.example.R;
import com.google.android.material.tabs.TabLayout;
import com.user.bean.HomeModelBean;
import com.user.bean.JiPaiBean;
import com.user.sqlite.DBHelper2;
import com.user.sqlite.DBHelper3;
import com.user.util.AdapterUtil;
import com.user.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recy, recy2;
    private TabLayout tab;
    private Button add;
    private int number = 0;
    private List<HomeModelBean> list = new ArrayList<>();
    private List<JiPaiBean> list2 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        initView(view);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                number = tab.getPosition();
                if (number == 2) {
                    recy.setVisibility(View.GONE);
                    recy2.setVisibility(View.VISIBLE);
                    setRecy2();
                } else {
                    recy.setVisibility(View.VISIBLE);
                    recy2.setVisibility(View.GONE);
                    setRecy();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        add.setOnClickListener(v -> {
            if (number == 2) {
                Intent intent = new Intent(getContext(), Add2.class);
                intent.putExtra("number", number);
                getActivity().startActivity(intent);
            } else {
                Intent intent = new Intent(getContext(), Add.class);
                intent.putExtra("number", number);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    private void setRecy() {
        list.clear();
        DBHelper2 dbHelper2 = new DBHelper2(getContext());
        SQLiteDatabase db = dbHelper2.getWritableDatabase();
        String sqlQuery = "SELECT * FROM job WHERE number = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{String.valueOf(number)});
        while (cursor.moveToNext()) {
            HomeModelBean bean = new HomeModelBean(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("salary")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getString(cursor.getColumnIndex("address"))
            );
            list.add(bean);
        }
        cursor.close();
        db.close();

        AdapterUtil<HomeModelBean> adapter = new AdapterUtil<>(R.layout.home_recy_item, ((data, position, holder) -> {
            TextView title = (TextView) holder.getView(R.id.title);
            TextView salary = (TextView) holder.getView(R.id.salary);
            TextView content = (TextView) holder.getView(R.id.content);
            ImageView touxiang = (ImageView) holder.getView(R.id.touxiang);
            TextView username = (TextView) holder.getView(R.id.username);
            TextView address = (TextView) holder.getView(R.id.address);
            LinearLayout layout = holder.getView(R.id.layout);
            title.setText(data.getTitle());
            salary.setText(data.getSalary());
            content.setText(data.getContent());
            touxiang.setImageResource(ImageUtil.getImage());
            username.setText(data.getUsername());
            address.setText(data.getAddress());
            layout.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), HomeDetail.class);
                intent.putExtra("bean", data);
                startActivity(intent);
            });
        }));
        adapter.listUpdate(list);
        recy.setAdapter(adapter);
    }

    private void setRecy2() {
        list2.clear();
        DBHelper3 dbHelper3 = new DBHelper3(getContext());
        SQLiteDatabase db = dbHelper3.getWritableDatabase();
        String sqlQuery = "SELECT * FROM jipai";
        Cursor cursor = db.rawQuery(sqlQuery, null);
        while (cursor.moveToNext()) {
            JiPaiBean bean = new JiPaiBean(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("price"))
            );
            list2.add(bean);
        }
        cursor.close();
        db.close();

        AdapterUtil<JiPaiBean> adapter = new AdapterUtil<>(R.layout.jipai_item, ((data, position, holder) -> {
            LinearLayout layout = (LinearLayout) holder.getView(R.id.layout);
            ImageView image = (ImageView) holder.getView(R.id.image);
            TextView title = (TextView) holder.getView(R.id.title);
            image.setImageResource(ImageUtil.getImage());
            title.setText(data.getTitle());
            layout.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), JiPaiDetail.class);
                intent.putExtra("bean", data);
                startActivity(intent);
            });
        }));
        adapter.listUpdate(list2);
        recy2.setAdapter(adapter);
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setVisibility(View.GONE);
        ((TextView) toolbar.getChildAt(1)).setText("首页");

        recy = view.findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        recy2 = view.findViewById(R.id.recy2);
        recy2.setLayoutManager(new GridLayoutManager(getContext(), 2));
        tab = view.findViewById(R.id.tab);
        setRecy();
        add = view.findViewById(R.id.add);
    }

    @Override
    public void onResume() {
        super.onResume();
        setRecy();
        setRecy2();
    }
}