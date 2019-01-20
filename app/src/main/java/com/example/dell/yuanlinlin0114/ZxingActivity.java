package com.example.dell.yuanlinlin0114;

import android.content.Intent;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import okhttp3.Interceptor;

public class ZxingActivity extends AppCompatActivity {

    private Button btn_sao;
    private Button btn_sheng;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        btn_sao = findViewById(R.id.btn_sao);
        btn_sheng = findViewById(R.id.btn_sheng);
        img = findViewById(R.id.img);

        btn_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saoMiao();
            }
        });
        btn_sheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageBitmap(encodeAs("1608c大神养成记"));
            }
        });
        }

    //扫描二维码
        public void saoMiao()
        {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.CODE_39);
            integrator.setPrompt("请扫描二维码");
            integrator.setCameraId(0);
            integrator.setBeepEnabled(true);
            integrator.setBarcodeImageEnabled(true);
            integrator.initiateScan();
        }
        //生成二维码
        public Bitmap encodeAs(String str) {
            Bitmap bitmap = null;
            BitMatrix result = null;
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 200, 200);
                // 使用 ZXing Android Embedded 要写的代码
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                bitmap = barcodeEncoder.createBitmap(result);
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException iae) {
                return null;
            }
            return bitmap;
        }

    @Override
    // 通过 onActivityResult的方法获取 扫描回来的 值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "内容为空", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "扫描成功", Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
