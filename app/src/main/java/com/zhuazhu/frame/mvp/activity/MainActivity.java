package com.zhuazhu.frame.mvp.activity;

import android.content.Intent;
import android.view.View;
import com.zhuazhu.frame.R;
import com.zhuazhu.frame.di.module.MainModule;
import com.zhuazhu.frame.mvp.application.FrameApplication;
import com.zhuazhu.frame.mvp.contract.MainContract;
import com.zhuazhu.frame.mvp.presenter.MainPresenterImp;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.TitleBarConfig;
import mejust.frame.annotation.TitleBarMenu;
import mejust.frame.mvp.view.BasePresenterActivity;
import mejust.frame.upgrade.ProgressMessageBuilder;
import mejust.frame.upgrade.ProgressType;
import mejust.frame.upgrade.UpgradeAppManager;
import mejust.frame.utils.log.Logger;
import mejust.frame.widget.title.TitleBarMenuLocation;

/**
 * @author wangpeifeng
 * @date 2018/04/18 9:25
 */
@LayoutId(R.layout.activity_main)
@TitleBarConfig(value = "首页", size = 15, color = R.color.c_000)
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
        mStatusBar.transparentBar().init();
    }

    @TitleBarMenu(location = TitleBarMenuLocation.leftFirstMenu, iconRes = R.drawable.loading_border)
    public void detail(View v) {
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
                //intent.setClass(this, PuppetActivity.class);
                //startActivity(intent);
                showToastDialog("你好", v1 -> Logger.i("点击确定"));
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
