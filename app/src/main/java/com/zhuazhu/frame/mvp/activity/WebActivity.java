package com.zhuazhu.frame.mvp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.zhuazhu.frame.R;
import mejust.frame.pay.FramePay;
import mejust.frame.pay.PayListener;
import mejust.frame.pay.info.WebPayInfo;
import mejust.frame.utils.JsonUtil;

public class WebActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint({ "AddJavascriptInterface", "SetJavaScriptEnabled" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        FrameLayout layout = findViewById(R.id.layout_web);
        webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new PayInterface(), "Android");
        layout.addView(webView);
    }

    private class PayInterface {

        @JavascriptInterface
        public void nativePay(String s) {
            WebPayInfo webPayInfo = JsonUtil.fromJson(s, WebPayInfo.class);
            FramePay.getInstance().pay(WebActivity.this, webPayInfo, new PayListener() {
                @Override
                public void onPaySuccess() {
                    System.out.println("支付成功");
                    webView.loadUrl(webPayInfo.getRedirectUrl());
                }

                @Override
                public void onPayError(int error_code, String message) {
                    System.out.println("支付失败--" + message);
                }

                @Override
                public void onPayCancel() {
                    System.out.println("支付取消");
                }
            });
        }
    }
}
