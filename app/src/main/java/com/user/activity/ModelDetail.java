package com.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.R;
import com.user.bean.ModelBean;

public class ModelDetail extends AppCompatActivity {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private TextView text7;
    private TextView text8;
    private TextView text9;
    private TextView text10;
    private TextView text11;
    private TextView text12;
    private TextView text13;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ModelBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_detail);
        bean = (ModelBean) getIntent().getSerializableExtra("bean");
        initView();
        initData();
    }

    private void initData() {
        text1.setText(bean.getChineseName());
        text2.setText(bean.getEnglishName());
        String sex = bean.getGender() ? "男" : "女";
        text3.setText("性别: " + sex);
        text4.setText("民族: " + bean.getNation());
        text5.setText("国籍: " + bean.getNationality());
        text6.setText("出生地: " + bean.getBirthplace());
        text7.setText("出生日期: " + bean.getData());
        text8.setText("毕业院校: " + bean.getSchool());
        text9.setText("星座: " + bean.getConstellation());
        text10.setText("身高: " + bean.getHeight());
        text11.setText("体重: " + bean.getWeight());
        text12.setText("职业: " + bean.getOccupation());
        text13.setText("主要成就: " + bean.getAchievements());

        image1.setImageResource(bean.getList().get(0));
        image2.setImageResource(bean.getList().get(1));
        image3.setImageResource(bean.getList().get(2));
        image4.setImageResource(bean.getList().get(3));
        image5.setImageResource(bean.getList().get(4));
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("详细");

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        text7 = (TextView) findViewById(R.id.text7);
        text8 = (TextView) findViewById(R.id.text8);
        text9 = (TextView) findViewById(R.id.text9);
        text10 = (TextView) findViewById(R.id.text10);
        text11 = (TextView) findViewById(R.id.text11);
        text12 = (TextView) findViewById(R.id.text12);
        text13 = (TextView) findViewById(R.id.text13);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);

    }
}