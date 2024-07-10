package com.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.user.bean.HomeModelBean;
import com.user.sqlite.DBHelper2;
import com.user.util.ImageUtil;

public class HomeDetail extends AppCompatActivity {

    private TextView title;
    private TextView salary;
    private ImageView touxiang;
    private TextView username;
    private TextView content;
    private TextView address;
    private AppCompatButton delete;
    private HomeModelBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        bean = (HomeModelBean) getIntent().getSerializableExtra("bean");
        initView();

        setData();

        delete.setOnClickListener(v -> {
            DBHelper2 dbHelper2 = new DBHelper2(getApplicationContext());
            SQLiteDatabase db = dbHelper2.getWritableDatabase();
            int idToDelete = bean.getId();
            String selection = "id=?";
            String[] selectionArgs = {String.valueOf(idToDelete)};
            int deletedRows = db.delete("job", selection, selectionArgs);
            if (deletedRows > 0) {
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
            }
            db.close();
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
        delete = (AppCompatButton) findViewById(R.id.delete);
    }
}