package mejust.frame.utils;

import android.app.Activity;
import android.content.Intent;
import conm.zhuazhu.common.utils.Utils;

/**
 * @author wangpeifeng
 * @date 2018/06/01 17:39
 */
public class CommonUtil {

    public static void startLoginActivity(Class<? extends Activity> cls) {
        if (cls == null) {
            return;
        }
        Intent intent = new Intent(Utils.getApp(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        Utils.getApp().startActivity(intent);
    }
}
