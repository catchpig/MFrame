package com.zhuazhu.frame.mvp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.zhuazhu.annotation.LayoutId;
import com.zhuazhu.frame.R;
import com.zhuazhu.frame.data.MFrameTitleBarOptions;
import com.zhuazhu.frame.di.module.MainModule;
import com.zhuazhu.frame.mvp.contract.MainContract;
import com.zhuazhu.frame.mvp.presenter.MainPresenterImp;
import com.zhuazhu.frame.mvp.application.FrameApplication;

import mejust.frame.mvp.view.BasePresenterActivity;
import mejust.frame.utils.log.Logger;

@LayoutId(R.layout.activity_main)
public class MainActivity extends BasePresenterActivity<MainPresenterImp>
        implements MainContract.View, View.OnClickListener {

    @Override
    protected void initParam() {
        MFrameTitleBarOptions options = new MFrameTitleBarOptions(this);
        options.setTitleString("正规的标题");
        options.setTextLeft("退出登录");
        options.setTextLeftSize(14);
        options.setTextLeftColor(Color.WHITE);
        options.setLeftTextListener(this);
        setTitleBar(options);
    }

    @Override
    protected void injectComponent() {
        FrameApplication.getAppComponent()
                .mainComponent(new MainModule(this))
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
            case R.id.mframe_titlebar_text_left:
                Logger.i("点击了左边文字");
                break;
            case R.id.recycler:
                intent.setClass(this, RecyclerActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
