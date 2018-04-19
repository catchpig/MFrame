package com.zhuazhu.frame.mvp.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.zhuazhu.frame.R;
import com.zhuazhu.frame.di.module.MainModuleBase;
import com.zhuazhu.frame.mvp.application.FrameApplication;
import com.zhuazhu.frame.mvp.contract.MainContract;
import com.zhuazhu.frame.mvp.presenter.MainPresenterImp;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.TitleBarConfig;
import mejust.frame.annotation.TitleBarMenu;
import mejust.frame.annotation.TitleBarMenuLocation;
import mejust.frame.mvp.view.BasePresenterActivity;
import mejust.frame.upgrade.ProgressMessageBuilder;
import mejust.frame.upgrade.ProgressType;
import mejust.frame.upgrade.UpgradeAppManager;

/**
 * @author wangpeifeng
 * @date 2018/04/18 9:25
 */
@LayoutId(R.layout.activity_main)
@TitleBarConfig(textValue = "https://www.cnblogs.com/Qian123/p/5710533.html")
public class MainActivity extends BasePresenterActivity<MainPresenterImp>
        implements MainContract.View {

    private static final String TAG = "MainActivity";

    @Override
    protected void initParam() {
    }

    @Override
    protected void injectComponent() {
        FrameApplication.getAppComponent().mainComponent(new MainModuleBase(this)).inject(this);
    }

    @Override
    protected void initView() {
    }

    @TitleBarMenu(location = TitleBarMenuLocation.rightSecondMenu, text = "第二")
    public void second(View v) {
        Log.i(TAG, "detail: ---------------------------------");
        showToastDialog("Hello World", null);
    }

    @TitleBarMenu(location = TitleBarMenuLocation.rightFirstMenu, text = "第一")
    public void detail(View v) {
        Log.i(TAG, "detail: ---------------------------------");
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
                startActivity(intent.setClass(this, PuppetActivity.class));
                break;
            case R.id.upgrade:
                new UpgradeAppManager(
                        "http://dl.mejust.com/weijie_app/weijie-pro.apk").setProgressManager(
                        ProgressType.NOTIFICATION,
                        new ProgressMessageBuilder(R.mipmap.ic_launcher, "app更新")).start(this);
                break;
            default:
                break;
        }
    }
}
