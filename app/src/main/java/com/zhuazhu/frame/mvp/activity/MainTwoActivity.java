package com.zhuazhu.frame.mvp.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import com.zhuazhu.frame.R;
import mejust.frame.widget.title.TitleBar;
import mejust.frame.widget.title.TitleBarMenuLocation;
import mejust.frame.widget.title.TitleBarSetting;

public class MainTwoActivity extends AppCompatActivity {

    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        titleBar = findViewById(R.id.title_bar);
        TitleBarSetting.TitleMenu titleMenu =
                new TitleBarSetting.TitleMenu(TitleBarMenuLocation.leftFirstMenu);
        titleMenu.setIconDrawable(ContextCompat.getDrawable(this, R.mipmap.back));
        TitleBarSetting titleBarSetting = new TitleBarSetting.Builder().setTitleTextContext("首页")
                .addTitleMenu(titleMenu)
                .build();
        titleBar.setTitleBarSetting(titleBarSetting);
    }
}
