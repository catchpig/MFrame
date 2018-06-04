package mejust.frame.config;

import android.app.Activity;

/**
 * @author wangpeifeng
 * @date 2018/05/30 14:26
 */
public class FrameConfig {

    /** 是否是debug版本 */
    private boolean isDebug;

    /** 登录页面 */
    private Class<? extends Activity> loginClass;

    private boolean openNetworkState;

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public Class<? extends Activity> getLoginClass() {
        return loginClass;
    }

    public void setLoginClass(Class<? extends Activity> loginClass) {
        this.loginClass = loginClass;
    }

    public boolean isOpenNetworkState() {
        return openNetworkState;
    }

    public void setOpenNetworkState(boolean openNetworkState) {
        this.openNetworkState = openNetworkState;
    }
}
