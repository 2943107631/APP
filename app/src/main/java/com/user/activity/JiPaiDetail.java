package com.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.R;
import com.user.bean.JiPaiBean;
import com.user.util.ImageUtil;

public class JiPaiDetail extends AppCompatActivity {

    private ImageView image;
    private TextView title;
    private ImageView touxiang;
    private TextView username;
    private TextView price;
    private TextView content;
    private TextView address;
    private JiPaiBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_pai_detail2);
        bean= (JiPaiBean) getIntent().getSerializableExtra("bean");
        initView();
        setData();
    }

    private void setData() {
        title.setText(bean.getTitle());
        username.setText(bean.getUsername());
        content.setText(bean.getContent());
        address.setText(bean.getAddress());
        touxiang.setImageResource(ImageUtil.getImage());
        image.setImageResource(ImageUtil.getImage());
        price.setText("寄拍价格:" + bean.getPrice());
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("");

        image = (ImageView) findViewById(R.id.image);
        title = (TextView) findViewById(R.id.title);
        touxiang = (ImageView) findViewById(R.id.touxiang);
        username = (TextView) findViewById(R.id.username);
        content = (TextView) findViewById(R.id.content);
        price = (TextView) findViewById(R.id.price);
        address = (TextView) findViewById(R.id.address);
    }
}