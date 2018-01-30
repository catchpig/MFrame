package mejust.frame.annotation;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建时间: 2018/01/30 15:19<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2018/01/30 15:19<br/>
 * 描述:
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({ PayCodeType.WE_PAY, PayCodeType.AL_PAY })
public @interface PayCodeType {

    /**
     * 微信支付
     */
    String WE_PAY = "02";
    /**
     * 阿里支付
     */
    String AL_PAY = "03";
}
