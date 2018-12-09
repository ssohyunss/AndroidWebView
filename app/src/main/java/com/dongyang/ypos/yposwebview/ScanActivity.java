package com.dongyang.ypos.yposwebview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(ZxingActivity.class);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult value = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String barcode = value.getContents();
        Intent intent = new Intent();
        if(null != barcode){
            intent.putExtra("result", barcode);
            setResult(RESULT_OK, intent);
        }else{
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }
}