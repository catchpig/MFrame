package com.zhuazhu.frame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.zhuazhu.annotation.LayoutId;
import com.zhuazhu.frame.data.HttpHelper;
import com.zhuazhu.frame.di.component.DaggerMainComponent;
import com.zhuazhu.frame.di.module.MainModule;
import javax.inject.Inject;
import mejust.frame.mvp.view.BaseActivity;
import mejust.frame.widget.TitleBarOptions;

@LayoutId(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder()
                .appComponent(FrameApplication.getAppComponent())
                .mainModule(new MainModule())
                .build()
                .inject(this);
        TitleBarOptions options = new TitleBarOptions();
        options.setTitleString("Hello World");

    }

    @Inject
    HttpHelper httpHelper;

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.image:
                intent.setClass(this, ImageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
