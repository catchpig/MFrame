package mejust.frame.annotation.utils;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import mejust.frame.annotation.StatusBar;

/**
 * 创建时间:2017/12/25 12:50<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/25 12:50<br/>
 * 描述:
 */

public class StatusBarUtils {
    /**
     * 判断当前类中是否有StatusBar注解
     */
    public static boolean isStatusBar(@NonNull Object obj) {
        StatusBar statusBar = AnnotionUtils.annotation(obj.getClass(), StatusBar.class);
        return statusBar != null;
    }

    /**
     * 判断是否需要自动设置灰色状态栏
     * <p>
     * 在#F0F0F0 和 #FFFFFF之间
     *
     * @param colorInt 状态栏颜色
     */
    public static boolean isDarkStatus(@ColorInt int colorInt) {
        int blue = Color.blue(colorInt);
        int red = Color.blue(colorInt);
        int green = Color.green(colorInt);
        return blue == red && red == green && blue <= 255 && blue >= 240;
    }
}
