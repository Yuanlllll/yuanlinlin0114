package com.example.dell.yuanlinlin0114;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.yuanlinlin0114.bean.LoginBean;
import com.example.dell.yuanlinlin0114.presenter.LoginPresenter;
import com.example.dell.yuanlinlin0114.view.Iview;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements Iview
{

    private Button btn_dl;
    private Button btn_qq;
    private EditText edit_name;
    private EditText edit_pwd;
    private ImageView qq_img;
    private TextView qq_name;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_name = findViewById(R.id.edit_name);
        edit_pwd = findViewById(R.id.edit_pwd);
        btn_dl = findViewById(R.id.btn_dl);
        btn_qq = findViewById(R.id.btn_qq);
        qq_img = findViewById(R.id.qq_img);
        qq_name = findViewById(R.id.name);

        loginPresenter = new LoginPresenter(this);
        btn_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getData();
            }

        });
        btn_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_name.getText().toString();
                String pwd = edit_pwd.getText().toString();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd))
                {
                    Toast.makeText(MainActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                   loginPresenter.getPdata(name,pwd);
                }
            }
        });
    }



    @Override
    public void getVdata(final String data)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson=new Gson();
                LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                Toast.makeText(MainActivity.this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
                if (loginBean.getMsg().equals("登录成功"))
                {
                    startActivity(new Intent(MainActivity.this,ShowActivity.class));
                    finish();
                }
            }
        });

    }

    private void getData()
    {
        UMShareAPI umShareAPI=UMShareAPI.get(MainActivity.this);
        umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Log.i("Tag",map+"");
                String name = map.get("name");
                String picurl = map.get("picurl");
                qq_name.setText(name);
                Glide.with(MainActivity.this).load(picurl).into(qq_img);

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(MainActivity.this).onActivityResult(requestCode,resultCode,data);
    }
    //防止内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenter!=null)
        {
            loginPresenter.destory();
        }
    }
}
