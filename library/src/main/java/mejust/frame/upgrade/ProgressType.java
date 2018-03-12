package mejust.frame.upgrade;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建时间: 2018/03/12 9:43<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/12 9:43<br>
 * 描述:
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({ ProgressType.NOTIFICATION, ProgressType.DIALOG })
public @interface ProgressType {

    /**
     * 通知栏
     */
    int NOTIFICATION = 0x01;
    /**
     * dialog
     */
    int DIALOG = 0x02;
}
