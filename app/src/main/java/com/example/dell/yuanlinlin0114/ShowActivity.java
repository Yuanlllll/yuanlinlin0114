package com.example.dell.yuanlinlin0114;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShowActivity extends AppCompatActivity {

    private Button btn_tiao;
    private Button btn_san;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        btn_tiao = findViewById(R.id.btn_tiao);
        btn_san = findViewById(R.id.btn_san);
        btn_tiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowActivity.this,ZxingActivity.class));
                finish();
            }
        });

        btn_san.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
