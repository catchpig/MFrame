package com.zhuazhu.frame.mvp.application;

import android.app.Activity;

import com.zhuazhu.frame.BuildConfig;
import com.zhuazhu.frame.FrameConfig;
import com.zhuazhu.frame.R;
import com.zhuazhu.frame.di.component.AppComponent;
import com.zhuazhu.frame.di.component.DaggerAppComponent;
import com.zhuazhu.frame.di.module.NetModule;

import mejust.frame.app.BaseApplication;
import mejust.frame.di.AppModule;
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

            }

            @Override
            public int statusBarColor() {
                return R.color.c_1e81d2;
            }

            @Override
            public TitleBarOptions titleBarOption() {
                return null;
            }
        });
    }
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        FrameConfig.init();
        super.onCreate();
        initImage();
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(getInstance()))
                    .netModule(new NetModule())
                    .build();
        }

        return appComponent;
    }

    /**
     * 初始化图片异步加载工具
     */
    public void initImage() {
        //        ImageUtils.setHostImageUrl(BuildConfig.IMAGE_URL);
        //        ImageUtils.setDefalutImage(R.mipmap.cacount);
        //        ImageUtils.setErrorImage(R.mipmap.cash_ing);
        ImageUtils.init(BuildConfig.IMAGE_URL, R.mipmap.cacount, R.mipmap.cash_ing);
    }
}
