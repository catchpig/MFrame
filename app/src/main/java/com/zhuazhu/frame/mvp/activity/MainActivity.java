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
import java.util.List;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.TextRightFirstEvent;
import mejust.frame.annotation.TitleBar;
import mejust.frame.annotation.TitleBarMenu;
import mejust.frame.mvp.view.BasePresenterActivity;
import mejust.frame.upgrade.ProgressMessageBuilder;
import mejust.frame.upgrade.ProgressType;
import mejust.frame.upgrade.UpgradeAppManager;
import mejust.frame.utils.JsonUtil;
import mejust.frame.utils.log.Logger;
import mejust.frame.widget.title.TitleBarMenuLocation;

@LayoutId(R.layout.activity_main)
@TitleBar(value = "首页", size = 15, color = R.color.c_000)
public class MainActivity extends BasePresenterActivity<MainPresenterImp>
        implements MainContract.View {

    @Override
    protected void initParam() {
        String user = "[{\"username\":\"132\"}]";
        List<User> list = JsonUtil.toList(user, User.class);
        System.out.println(list);
    }

    @Override
    protected void injectComponent() {
        FrameApplication.getAppComponent().mainComponent(new MainModule(this)).inject(this);
    }

    @Override
    protected void initView() {
        User user = new User();

        mStatusBar.transparentBar().init();
    }

    @TextRightFirstEvent("详情")
    public void detail(TextView v) {
        showToastDialog("Hello World", null);
    }

    @TitleBarMenu(location = TitleBarMenuLocation.leftFirstMenu, iconRes = R.drawable.loading_border)
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
