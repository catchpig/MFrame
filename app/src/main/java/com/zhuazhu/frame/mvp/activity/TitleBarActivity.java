package com.zhuazhu.frame.mvp.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.zhuazhu.frame.R;
import mejust.frame.annotation.TitleBarConfig;
import mejust.frame.annotation.TitleBarMenu;
import mejust.frame.utils.TitleBarUtil;
import mejust.frame.widget.title.TitleBar;
import mejust.frame.annotation.TitleBarMenuLocation;
import mejust.frame.widget.title.TitleBarSetting;

/**
 * @author wangpeifeng
 * @date 2018/04/18 9:50
 */
@TitleBarConfig(value = "测试", textColor = R.color.colorAccent)
public class TitleBarActivity extends AppCompatActivity {

    private static final String TAG = "TitleBarActivity";

    public TitleBar titleBar;
    public TitleBarSetting titleBarSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_bar);
        titleBar = findViewById(R.id.title_bar);
        TitleBarSetting.TitleMenu titleMenu =
                new TitleBarSetting.TitleMenu(TitleBarMenuLocation.leftFirstMenu);
        titleMenu.setIconDrawable(ContextCompat.getDrawable(this, R.mipmap.back));
        titleMenu.setClickListener(v -> finish());
        titleBarSetting = new TitleBarSetting.Builder().addTitleMenu(titleMenu).build();
        TitleBarUtil.inject(this, titleBar, titleBarSetting);
        Log.i(TAG, "onCreate: titleBarSetting--" + titleBarSetting.toString());
    }

    @TitleBarMenu(location = TitleBarMenuLocation.rightSecondMenu, text = "退出")
    public void clickToast(View view) {
        Toast.makeText(this, "showToast", Toast.LENGTH_SHORT).show();
    }

    @TitleBarMenu(location = TitleBarMenuLocation.rightFirstMenu, text = "登录")
    public void clickSecond(View view) {
        Toast.makeText(this, "showToast", Toast.LENGTH_SHORT).show();
    }
}
