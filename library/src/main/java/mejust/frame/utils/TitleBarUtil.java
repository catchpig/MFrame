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
    public static TitleBarSetting inject(Activity activity, TitleBar titleBar,
            TitleBarSetting setting) {
        try {
            String className = activity.getClass().getName() + "_TitleBarInject";
            Class cls = Class.forName(className);
            Method method = cls.getMethod("getTitleBar", activity.getClass(), setting.getClass());
            setting = (TitleBarSetting) method.invoke(cls.newInstance(), activity, setting);
            titleBar.setTitleBarSetting(setting);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setting;
    }
}
