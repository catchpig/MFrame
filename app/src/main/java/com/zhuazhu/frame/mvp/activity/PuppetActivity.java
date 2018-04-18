package com.zhuazhu.frame.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.zhuazhu.frame.R;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.StatusBarConfig;
import mejust.frame.mvp.view.BaseActivity;
import mejust.frame.widget.title.StatusBar;

/**
 * @author wangpeifeng
 * @date 2018/04/18 17:12
 */
@LayoutId(R.layout.activity_puppet)
@StatusBarConfig(isInitActivity = false)
public class PuppetActivity extends BaseActivity {
    private String url = "http://image.tianjimedia.com/uploadImages/2015/285/24/586K2UOWHG9D.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.setVisibility(View.GONE);
        mStatusBar = StatusBar.with(this);
        mStatusBar.init();
    }
}
