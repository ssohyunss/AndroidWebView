package com.dongyang.ypos.yposwebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.yposWebView);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings(); // WebSetting : 캐시, 자바스크립트등의 기능 설정
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("http://13.124.251.39:8080/project/");
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
}
