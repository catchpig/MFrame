package mejust.frame.annotation;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author wangpeifeng
 * @date 2018/04/18 11:04
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        TitleBarMenuLocation.leftFirstMenu, TitleBarMenuLocation.leftSecondMenu,
        TitleBarMenuLocation.rightFirstMenu, TitleBarMenuLocation.rightSecondMenu
})
public @interface TitleBarMenuLocation {

    /** 左边第一个，默认为返回键位置 */
    int leftFirstMenu = 0x01;
    /** 左边第二个 */
    int leftSecondMenu = 0x02;
    /** 右边第一个 */
    int rightFirstMenu = 0x11;
    /** 右边第二个 */
    int rightSecondMenu = 0x12;
}
