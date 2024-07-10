package com.user.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.user.MainActivity;
import com.example.R;
import com.user.activity.DianZan;
import com.user.activity.My;
import com.user.activity.Password;
import com.user.activity.Personal;
import com.user.activity.Resume;
import com.user.sqlite.DBHelper1;

public class PersonFragment extends Fragment {

    private ImageView image;
    private TextView text1;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout5;
    private LinearLayout layout6;
    private AppCompatButton layout3;
    private LinearLayout layout4;
    private AppCompatButton btn;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        initView(view);
        id = getActivity().getIntent().getIntExtra("id", 0);
        setData();
        // 进入到用户信息
        layout1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Personal.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
        // 进入到修改密码
        layout2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Password.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
        // 进入到个人简历
        layout3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Resume.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
        // 进入到我的点赞界面
        layout4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DianZan.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
        // 进入到我的作品界面
        layout5.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), My.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
        // 退出应用
        layout6.setOnClickListener(v -> {
            getActivity().finish();
        });

        btn.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        });

        return view;
    }

    private void setData() {
        DBHelper1 dbHelper = new DBHelper1(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            String username = cursor.getString(cursor.getColumnIndex("username"));
            text1.setText(username);
        }
        dbHelper.close();
        cursor.close();
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setVisibility(View.GONE);
        ((TextView) toolbar.getChildAt(1)).setText("个人中心");

        image = (ImageView) view.findViewById(R.id.image);
        text1 = (TextView) view.findViewById(R.id.text1);
        layout1 = (LinearLayout) view.findViewById(R.id.layout1);
        layout2 = (LinearLayout) view.findViewById(R.id.layout2);
        layout3 = (AppCompatButton) view.findViewById(R.id.layout3);
        layout4 = (LinearLayout) view.findViewById(R.id.layout4);
        layout5 = (LinearLayout) view.findViewById(R.id.layout5);
        layout6 = (LinearLayout) view.findViewById(R.id.layout6);
        btn = (AppCompatButton) view.findViewById(R.id.btn);
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }
}