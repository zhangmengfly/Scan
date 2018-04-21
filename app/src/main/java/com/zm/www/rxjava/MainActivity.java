package com.zm.www.rxjava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;


public class MainActivity extends AppCompatActivity {

    private ImageView iv_test;
    private Button btn_scan_code;
    private Button btn_create_code;
    private TextView tv_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){


        iv_test=(ImageView) findViewById(R.id.iv_test);
        btn_create_code=(Button) findViewById(R.id.btn_create_code);
        btn_scan_code=(Button) findViewById(R.id.btn_scan_code);
        tv_test=(TextView)findViewById(R.id.tv_test) ;

        btn_create_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ZXingUtils.createBitmap("hahahaha");
                iv_test.setImageBitmap(bitmap);
            }
        });

        btn_scan_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(MainActivity.this)
                        .setOrientationLocked(false)
                        .setCaptureActivity(ScanActivity.class) // 设置自定义的activity是ScanActivity
                        .initiateScan(); // 初始化扫描
            }
        });



    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this,"内容为空",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"扫描成功",Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
                tv_test.setText(ScanResult);
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }



}
