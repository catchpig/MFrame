package com.zhuazhu.frame;

import com.zhuazhu.frame.di.component.AppComponent;
import com.zhuazhu.frame.di.component.DaggerAppComponent;
import com.zhuazhu.frame.di.module.NetModule;

import mejust.frame.BaseApplication;
import mejust.frame.di.AppModule;
import mejust.frame.image.ImageUtils;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:39
 */

public class FrameApplication extends BaseApplication {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        FrameConfig.DEBUG = BuildConfig.DEBUG;
        super.onCreate();
        initImage();
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(getApplication()))
                    .netModule(new NetModule())
                    .build();
        }

        return appComponent;
    }

    /**
     * 初始化图片异步加载工具
     */
    public void initImage(){
//        ImageUtils.setHostImageUrl(BuildConfig.IMAGE_URL);
//        ImageUtils.setDefalutImage(R.mipmap.cacount);
//        ImageUtils.setErrorImage(R.mipmap.cash_ing);
        ImageUtils.init(BuildConfig.IMAGE_URL,R.mipmap.cacount,R.mipmap.cash_ing);
    }
}
