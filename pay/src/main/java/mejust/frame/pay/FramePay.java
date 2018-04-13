package mejust.frame.pay;

import android.app.Activity;
import android.support.annotation.NonNull;
import mejust.frame.pay.alipay.AliPay;
import mejust.frame.pay.wechat.WeChatParam;
import mejust.frame.pay.wechat.WechatPay;

/**
 * 创建时间: 2018/04/13
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/13
 * 描述: <empty/>
 */
public class FramePay {

    private static final class SingleHolder {
        private static final FramePay instance = new FramePay();
    }

    public static FramePay getInstance() {
        return SingleHolder.instance;
    }

    public void payAli(@NonNull Activity activity, @NonNull String param,
            @NonNull PayListener payListener) {
        new AliPay(activity, payListener).startAliPay(param);
    }

    public void payWechat(@NonNull Activity activity, @NonNull WeChatParam weChatParam,
            @NonNull PayListener payListener) {
        new WechatPay(activity, weChatParam.getAppId(), payListener).startWXPay(weChatParam);
    }
}
