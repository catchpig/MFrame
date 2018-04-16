package mejust.frame.pay.info;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建时间: 2018/04/16
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/16
 * 描述: <empty/>
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({ WebPayStatus.PAY_CANCEL, WebPayStatus.PAY_SUCCESS, WebPayStatus.PAY_FAIL })
public @interface WebPayStatus {
    /**
     * 支付取消
     */
    String PAY_CANCEL = "1000";
    /**
     * 支付成功
     */
    String PAY_SUCCESS = "1001";
    /**
     * 支付失败
     */
    String PAY_FAIL = "1002";
}
