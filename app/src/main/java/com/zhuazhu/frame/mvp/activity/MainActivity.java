package com.zhuazhu.frame.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.zhuazhu.frame.R;
import com.zhuazhu.frame.di.module.MainModule;
import com.zhuazhu.frame.model.User;
import com.zhuazhu.frame.mvp.application.FrameApplication;
import com.zhuazhu.frame.mvp.contract.MainContract;
import com.zhuazhu.frame.mvp.presenter.MainPresenterImp;

import lombok.NonNull;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.TextRightFirstEvent;
import mejust.frame.annotation.TitleBar;
import mejust.frame.mvp.view.BasePresenterActivity;
import mejust.frame.upgrade.ProgressMessageBuilder;
import mejust.frame.upgrade.ProgressType;
import mejust.frame.upgrade.UpgradeAppManager;

@LayoutId(R.layout.activity_main)
@TitleBar(value = "首页", hiddenBack = true)
public class MainActivity extends BasePresenterActivity<MainPresenterImp>
        implements MainContract.View {

    @Override
    protected void initParam() {

    }

    @Override
    protected void injectComponent() {
        FrameApplication.getAppComponent().mainComponent(new MainModule(this)).inject(this);
    }

    @Override
    protected void initView() {
        @NonNull
        User user = null;


        mStatusBar.transparentBar().init();
    }

    @TextRightFirstEvent("详情")
    public void detail(TextView v) {
        showToastDialog("Hello World", null);
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.image:
                intent.setClass(this, ImageActivity.class);
                startActivity(intent);
                break;
            case R.id.recycler:
                intent.setClass(this, RecyclerActivity.class);
                startActivity(intent);
                break;
            case R.id.puppet:
                intent.setClass(this, PuppetActivity.class);
                startActivity(intent);
                break;
            case R.id.upgrade:
                new UpgradeAppManager(
                        "http://dl.mejust.com/weijie_app/weijie-pro.apk").setProgressManager(
                        ProgressType.DIALOG,
                        new ProgressMessageBuilder(R.mipmap.ic_launcher, "app更新")).start(this);
                break;
            default:
                break;
        }
    }
}
