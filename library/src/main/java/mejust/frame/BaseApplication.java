package mejust.frame;

import android.support.annotation.CallSuper;

import mejust.frame.cash.CrashHandler;

/**
 * @author : Beaven
 * @date : 2017-12-19 21:42
 */

public abstract class BaseApplication extends com.zhuazhu.application.BaseApplication {

    private static BaseApplication application;
    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initCash();
    }

    public static BaseApplication getApplication() {
        return application;
    }

    public void initCash(){
        CrashHandler.getInstance().init(getApplicationContext());
    }
}
