package com.example.webviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);

        //呼叫getSettings()取得WebSettings物件
        //再呼叫setJavaScriptEnabled(true)啟用JavaScript功能
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://www.google.com");


        //呼叫setWebViewClient()才能以WebView來開啟網頁並點擊其超連結
        //否則會以手機內建(系統)的瀏覽器來開啟網頁或點擊其超連結
        webView.setWebViewClient(new WebViewClient() {

            @Override //傳入的參數為被點擊的WebView與其被點擊的超連結
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }


    //預設按返回鍵會返回前一個Activity
    //所以改寫onKeyDown()來判斷是否按下返回鍵(KEYCODE_BACK)以及是否可以回到上一個網頁
    //沒有前一個網頁的情況下，呼叫父類別的同名方法進行預設的返回鍵處理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true; //回傳true表示事件到此為止，不會在向後延續(結束此方法)
        }
        return super.onKeyDown(keyCode, event);
    }
}

