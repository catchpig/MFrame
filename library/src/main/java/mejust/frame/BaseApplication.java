package mejust.frame;

import android.support.annotation.CallSuper;

import mejust.frame.cash.CrashHandler;

import mejust.frame.app.AppConfig;
import mejust.frame.utils.log.DebugLogTree;
import mejust.frame.utils.log.ReleaseLogTree;
import timber.log.Timber;

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
        setLog();
    }

    public static BaseApplication getApplication() {
        return application;
    }

    public void initCash(){
        CrashHandler.getInstance().init(getApplicationContext());
    }

    private void setLog() {
        if (AppConfig.DEBUG) {
            Timber.plant(new DebugLogTree());
        } else {
            Timber.plant(new ReleaseLogTree());
        }
    }
}
