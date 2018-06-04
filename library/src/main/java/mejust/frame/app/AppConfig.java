package mejust.frame.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import conm.zhuazhu.common.utils.Utils;
import mejust.frame.common.log.DebugLogTree;
import mejust.frame.common.log.ReleaseLogTree;
import mejust.frame.widget.ToastFrame;
import mejust.frame.widget.title.TitleBarSetting;
import timber.log.Timber;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:16
 * <p>
 */
public final class AppConfig {

    /**
     * 程序运行状态区分
     */
    public static boolean DEBUG = false;

    /**
     * 网络请求详情打印
     */
    public static String URL_LOG = "OkHttp";

    /**
     * 连接超时，单位：秒
     */
    public static long CONNECT_TIME_OUT_DEFAULT = 10;

    /**
     * 读取超时，单位：秒
     */
    public static long READ_TIME_OUT_DEFAULT = 10;

    /**
     * 写入超时，单位：秒
     */
    public static long WRITE_TIME_OUT_DEFAULT = 10;

    /**
     * json解析时间格式
     */
    public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 网络状态监听标志判断
     */
    public static boolean NETWORK_STATUS_MONITORING = true;

    /**
     * 登录Activity
     */
    private static Class<? extends Activity> loginClass = null;

    /**
     * title配置
     */
    private static TitleBarSetting titleBarSetting = new TitleBarSetting.Builder().build();

    public static void init(Application application, ConfigInterface configInterface) {
        setConfigInterface(configInterface);
        Utils.init(application);
        CrashHandler.getInstance().init(application);
        ToastFrame.init(application);
        if (AppConfig.DEBUG) {
            Timber.plant(new DebugLogTree());
        } else {
            Timber.plant(new ReleaseLogTree());
        }
    }

    public static TitleBarSetting getTitleBarSetting() {
        return titleBarSetting.newBuilder().build();
    }

    public static void startLoginActivity(Context context) {
        Class<? extends Activity> loginClass = AppConfig.loginClass;
        if (loginClass == null) {
            throw new IllegalArgumentException("login Activity class is null,please set");
        }
        context.startActivity(new Intent(context, loginClass));
    }

    private static void setConfigInterface(ConfigInterface configInterface) {
        AppConfig.DEBUG = configInterface.isAppDebug();
        AppConfig.loginClass = configInterface.getLoginActivityClass();
        AppConfig.titleBarSetting = configInterface.getTitleBarSetting();
    }
}
