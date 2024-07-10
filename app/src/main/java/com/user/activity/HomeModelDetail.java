package com.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.user.bean.HomeModelBean;
import com.user.util.ImageUtil;

public class HomeModelDetail extends AppCompatActivity {

    private TextView title;
    private TextView salary;
    private ImageView touxiang;
    private TextView username;
    private TextView content;
    private TextView address;
    private AppCompatButton sending;
    private HomeModelBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_model_detail);
        bean = (HomeModelBean) getIntent().getSerializableExtra("bean");
        initView();

        setData();

        sending.setOnClickListener(v -> {
            Toast.makeText(this, "发送简历成功", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void setData() {
        title.setText(bean.getTitle());
        salary.setText(bean.getSalary());
        username.setText(bean.getUsername());
        content.setText(bean.getContent());
        address.setText(bean.getAddress());
        touxiang.setImageResource(ImageUtil.getImage());
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(v -> finish());
        ((TextView) toolbar.getChildAt(1)).setText("");

        title = (TextView) findViewById(R.id.title);
        salary = (TextView) findViewById(R.id.salary);
        touxiang = (ImageView) findViewById(R.id.touxiang);
        username = (TextView) findViewById(R.id.username);
        content = (TextView) findViewById(R.id.content);
        address = (TextView) findViewById(R.id.address);
        sending = (AppCompatButton) findViewById(R.id.sending);
    }
}