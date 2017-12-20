package com.zhuazhu.frame;

import com.zhuazhu.frame.di.module.NetModule;
import com.zhuazhu.frame.di.component.AppComponent;
import com.zhuazhu.frame.di.component.DaggerAppComponent;
import mejust.frame.BaseApplication;
import mejust.frame.di.module.AppModule;

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
}
