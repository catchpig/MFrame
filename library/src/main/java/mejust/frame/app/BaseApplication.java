package mejust.frame.app;

import android.app.Application;
import android.support.annotation.CallSuper;
import conm.zhuazhu.common.utils.Utils;
import mejust.frame.utils.log.DebugLogTree;
import mejust.frame.utils.log.ReleaseLogTree;
import mejust.frame.widget.ToastMsg;
import timber.log.Timber;

/**
 * 创建时间:2017-12-21 10:41<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017-12-21 10:41<br/>
 * 描述: 无MVP的基类<br/>
 */

public abstract class BaseApplication extends Application {

    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initCash();
        initLog();
        ToastMsg.init(this);
    }

    /**
     * 初始化异常捕获抓取
     */
    @CallSuper
    public void initCash() {
        CrashHandler.getInstance().init(getApplicationContext());
    }

    /**
     * 初始化日志打印
     */
    @CallSuper
    private void initLog() {
        if (AppConfig.DEBUG) {
            Timber.plant(new DebugLogTree());
        } else {
            Timber.plant(new ReleaseLogTree());
        }
    }
}
