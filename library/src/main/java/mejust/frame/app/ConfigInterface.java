package mejust.frame.app;

import android.app.Activity;
import mejust.frame.widget.title.TitleBarSetting;

/**
 * @author wangpeifeng
 * @date 2018/04/18 15:18
 */
public interface ConfigInterface {

    /**
     * check app debug mode
     *
     * @return true
     */
    boolean isAppDebug();

    /**
     * 登录LoginActivity.class
     *
     * @return Activity
     */
    Class<? extends Activity> getLoginActivityClass();

    /**
     * 全局的TitleBarSetting
     *
     * @return TitleBarSetting
     */
    TitleBarSetting getTitleBarSetting();
}
