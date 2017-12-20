package mejust.frame;

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
    }

    public static BaseApplication getApplication() {
        return application;
    }
}
