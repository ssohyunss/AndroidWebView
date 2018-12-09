package com.dongyang.ypos.yposwebview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    public class WebAppInterface {
        Context mContext;
        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }
        /** Show a toast from the web page */
        @JavascriptInterface
        public void writeBarCode() {
            Intent intent = new Intent(mContext, ScanActivity.class);
            startActivityForResult(intent,1);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.yposWebView);

        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        WebSettings webSettings = webView.getSettings(); // WebSetting : 캐시, 자바스크립트등의 기능 설정
        webSettings.setJavaScriptEnabled(true);

        final Context myApp = this;


        //Alert창 코딩 부분
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
                new AlertDialog.Builder(myApp)
                        .setTitle("AlertDialog")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                new AlertDialog.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        result.confirm();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();

                return true;
            }
        });

        webView.loadUrl("http://10.0.2.2:8080/project/");
/**
 webView.setWebViewClient(new WebViewClient() { // 새 창이 뜨는 것을 방지

 @Override
 public boolean shouldOverrideUrlLoading(WebView view, String url) {

 view.loadUrl(url);
 return true;
 }

 });

 webView.setOnTouchListener(new View.OnTouchListener() {
 @Override
 public boolean onTouch(View v, MotionEvent event) {

 Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_SHORT).show();
 return false;
 }
 });

 webView.loadUrl("https://redmuffler.tistory.com/");**/

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // QR코드/바코드를 스캔한 결과 값을 가져옵니다.
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Toast.makeText(MainActivity.this, "결과가 성공이 아님.", Toast.LENGTH_SHORT).show();
            return;
        }else{
            String value = data.getStringExtra("result");
            webView.loadUrl("javascript:barcodeText('"+value+"');");
        }

    }
}
