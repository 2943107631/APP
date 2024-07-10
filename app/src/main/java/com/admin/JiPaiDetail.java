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
import com.user.bean.JiPaiBean;
import com.user.sqlite.DBHelper2;
import com.user.sqlite.DBHelper3;
import com.user.util.ImageUtil;

public class JiPaiDetail extends AppCompatActivity {

    private ImageView image;
    private TextView title;
    private ImageView touxiang;
    private TextView username;
    private TextView price;
    private TextView content;
    private TextView address;
    private AppCompatButton delete;
    private JiPaiBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_pai_detail);
        bean = (JiPaiBean) getIntent().getSerializableExtra("bean");
        initView();

        setData();

        delete.setOnClickListener(v -> {
            DBHelper3 dbHelper3 = new DBHelper3(getApplicationContext());
            SQLiteDatabase db = dbHelper3.getWritableDatabase();
            int idToDelete = bean.getId();
            String selection = "id=?";
            String[] selectionArgs = {String.valueOf(idToDelete)};
            int deletedRows = db.delete("jipai", selection, selectionArgs);
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
        delete = (AppCompatButton) findViewById(R.id.delete);
    }
}