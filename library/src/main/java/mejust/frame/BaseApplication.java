package mejust.frame;

import mejust.frame.app.AppConfig;
import mejust.frame.log.DebugLogTree;
import mejust.frame.log.ReleaseLogTree;
import timber.log.Timber;

/**
 * @author : Beaven
 * @date : 2017-12-19 21:42
 */

public abstract class BaseApplication extends com.zhuazhu.application.BaseApplication {

    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        setLog();
    }

    public static BaseApplication getApplication() {
        return application;
    }

    private void setLog() {
        if (AppConfig.DEBUG) {
            Timber.plant(new DebugLogTree());
        } else {
            Timber.plant(new ReleaseLogTree());
        }
    }
}
