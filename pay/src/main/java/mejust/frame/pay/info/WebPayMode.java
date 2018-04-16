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
@StringDef({ WebPayMode.WECHAT_PAY, WebPayMode.ALI_PAY })
public @interface WebPayMode {

    /** 微信支付 */
    String WECHAT_PAY = "02";
    /** 支付宝支付 */
    String ALI_PAY = "03";
}
