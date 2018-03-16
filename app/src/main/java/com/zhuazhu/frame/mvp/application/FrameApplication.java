package com.zhuazhu.frame.mvp.application;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zhuazhu.frame.BuildConfig;
import com.zhuazhu.frame.FrameConfig;
import com.zhuazhu.frame.R;
import com.zhuazhu.frame.di.component.AppComponent;
import com.zhuazhu.frame.di.component.DaggerAppComponent;
import com.zhuazhu.frame.di.module.NetModule;
import com.zhuazhu.frame.mvp.activity.MainActivity;

import conm.zhuazhu.common.utils.Utils;
import mejust.frame.app.BaseApplication;
import mejust.frame.di.module.AppModule;
import mejust.frame.image.ImageUtils;
import mejust.frame.mvp.view.BaseActivity;
import mejust.frame.mvp.view.option.DefaultActivityOption;
import mejust.frame.widget.title.TitleBarOptions;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:39
 */

public class FrameApplication extends BaseApplication {
    static {

        BaseActivity.setDefaultActivityOption(new DefaultActivityOption() {
            @Override
            public void login(Activity activity) {
                activity.startActivity(new Intent(activity, MainActivity.class));
            }

            @NonNull
            @Override
            public TitleBarOptions titleBarOption() {
                TitleBarOptions options = new TitleBarOptions();
                options.setBackgroundColor(R.color.white);
                options.setTextColor(R.color.c_000);
                options.setTitleTextSize(18);
                options.setBackImage(R.mipmap.back);
                options.setBackTextSize(15);
                options.setRightTextSize(15);
                options.setBackText("返回");
                return options;
            }
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            ClassicsHeader classicsHeader = new ClassicsHeader(context);
            layout.setEnableHeaderTranslationContent(false);
            layout.setPrimaryColors(Color.parseColor("#333333"), Color.parseColor("#7898ab"));
            return classicsHeader;
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater(
                (context, layout) -> new BallPulseFooter(context));
    }

    @Override
    public void onCreate() {
        FrameConfig.init();
        super.onCreate();
        initImage();
    }

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(Utils.getApp()))
                    .netModule(new NetModule())
                    .build();
        }
        return appComponent;
    }

    /**
     * 初始化图片异步加载工具
     */
    public void initImage() {
        ImageUtils.init(BuildConfig.IMAGE_URL, R.mipmap.cacount, R.mipmap.cash_ing);
    }
}
