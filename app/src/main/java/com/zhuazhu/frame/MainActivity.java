package com.zhuazhu.frame;

import android.content.Intent;
import android.view.View;
import com.zhuazhu.annotation.LayoutId;
import com.zhuazhu.frame.data.MFrameTitleBarOptions;
import com.zhuazhu.frame.di.component.DaggerMainComponent;
import com.zhuazhu.frame.di.module.MainModule;
import com.zhuazhu.frame.mvp.MainContract;
import com.zhuazhu.frame.mvp.MainPresenter;
import mejust.frame.mvp.view.BasePresenterActivity;

@LayoutId(R.layout.activity_main)
public class MainActivity extends BasePresenterActivity<MainPresenter>
        implements MainContract.View, View.OnClickListener {

    @Override
    protected void initData() {
        MFrameTitleBarOptions options = new MFrameTitleBarOptions(this);
        options.setTitleString("Hello World");
        setTitleBar(options);
    }

    @Override
    protected void injectComponent() {
        DaggerMainComponent.builder()
                .appComponent(FrameApplication.getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {

    }

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
