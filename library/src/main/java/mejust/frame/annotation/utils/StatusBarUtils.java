package mejust.frame.annotation.utils;

import android.app.Activity;
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
     * @param activity
     * @return
     */
    public static boolean isStatusBar(@NonNull Activity activity){
        StatusBar statusBar = AnnotionUtils.annotation(activity.getClass(),StatusBar.class);
        if(statusBar==null){
            return false;
        }
        return true;
    }
}
