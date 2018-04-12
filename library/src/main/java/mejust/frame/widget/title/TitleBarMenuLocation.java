package mejust.frame.widget.title;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建时间: 2018/04/12
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/12
 * 描述: <empty/>
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
