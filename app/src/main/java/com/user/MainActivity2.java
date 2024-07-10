package com.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.R;
import com.user.fragment.ExChangeFragment;
import com.user.fragment.HomeFragment;
import com.user.fragment.MoChaFragment;
import com.user.fragment.ModelFragment;
import com.user.fragment.PersonFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private List<Fragment> list = new ArrayList<>();
    private ViewPager2 vp2;
    private BottomNavigationView jiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();

        // 作为一个容器 点击下面的底部导航栏
        jiao.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    vp2.setCurrentItem(0);
                    break;
                case R.id.model:
                    vp2.setCurrentItem(1);
                    break;
                case R.id.mocha:
                    vp2.setCurrentItem(2);
                    break;
                case R.id.exchange:
                    vp2.setCurrentItem(3);
                    break;
                case R.id.person:
                    vp2.setCurrentItem(4);
                    break;
            }
            return true;
        });
    }

    // 存储内容
    private void initData() {
        list.add(new HomeFragment());
        list.add(new ModelFragment());
        list.add(new MoChaFragment());
        list.add(new ExChangeFragment());
        list.add(new PersonFragment());
        vp2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return list.get(position);
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });
    }

    private void initView() {
        vp2 = findViewById(R.id.vp2);
        vp2.setOffscreenPageLimit(5);
        vp2.setUserInputEnabled(false);
        jiao = (BottomNavigationView) findViewById(R.id.jiao);
    }
}