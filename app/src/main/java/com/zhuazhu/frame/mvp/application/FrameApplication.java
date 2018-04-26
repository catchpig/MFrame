package com.zhuazhu.frame.mvp.application;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.zhuazhu.frame.BuildConfig;
import com.zhuazhu.frame.R;
import com.zhuazhu.frame.di.component.AppComponent;
import com.zhuazhu.frame.di.component.DaggerAppComponent;
import com.zhuazhu.frame.di.module.NetModule;
import com.zhuazhu.frame.mvp.activity.RecyclerActivity;
import conm.zhuazhu.common.utils.Utils;
import mejust.frame.annotation.TitleBarMenuLocation;
import mejust.frame.app.AppConfig;
import mejust.frame.app.ConfigInterface;
import mejust.frame.di.module.AppModule;
import mejust.frame.image.ImageUtils;
import mejust.frame.widget.title.TitleBarSetting;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:39
 */

public class FrameApplication extends Application implements ConfigInterface {

    static {
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
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        AppConfig.init(this, this);
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

    @Override
    public boolean isAppDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public Class<? extends Activity> getLoginActivityClass() {
        return RecyclerActivity.class;
    }

    @Override
    public TitleBarSetting getTitleBarSetting() {
        TitleBarSetting.TitleMenu titleMenu =
                new TitleBarSetting.TitleMenu(TitleBarMenuLocation.leftFirstMenu);
        titleMenu.setIconDrawableRes(getApplicationContext(), R.drawable.ic_arrow_back_white_24dp);
        titleMenu.setClickListener(v -> {
            Activity activity = Utils.getTopActivity();
            if (activity != null) {
                activity.finish();
            }
        });
        return new TitleBarSetting.Builder().addTitleMenu(titleMenu).build();
    }
}
