package mejust.frame.utils;

import android.app.Activity;
import java.lang.reflect.Method;
import mejust.frame.widget.title.TitleBar;
import mejust.frame.widget.title.TitleBarSetting;

/**
 * @author wangpeifeng
 * @date 2018/04/18 9:22
 */
public class TitleBarUtil {

    @SuppressWarnings("unchecked")
    public static void inject(Activity activity, TitleBar titleBar, TitleBarSetting setting) {
        TitleBarSetting titleBarSetting = setting;
        try {
            String className = activity.getClass().getName() + "_TitleBarInject";
            Class cls = Class.forName(className);
            Method method =
                    cls.getMethod("getTitleBar", activity.getClass(), titleBarSetting.getClass());
            titleBarSetting =
                    (TitleBarSetting) method.invoke(cls.newInstance(), activity, titleBarSetting);
            titleBar.setTitleBarSetting(titleBarSetting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
