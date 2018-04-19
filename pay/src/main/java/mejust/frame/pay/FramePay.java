package mejust.frame.pay;

import android.app.Activity;
import android.support.annotation.NonNull;
import mejust.frame.pay.alipay.AliPay;
import mejust.frame.pay.info.WebPayInfo;
import mejust.frame.pay.info.WebPayMode;
import mejust.frame.pay.wechat.WeChatParam;
import mejust.frame.pay.wechat.WechatPay;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author wangpeifeng
 * @date 2018/04/19 9:38
 */
public class FramePay {

    private static final class SingleHolder {
        private static final FramePay INSTANCE = new FramePay();
    }

    public static FramePay getInstance() {
        return SingleHolder.INSTANCE;
    }

    /**
     * 支付宝支付
     */
    public void payAli(@NonNull Activity activity, @NonNull String param,
            @NonNull PayListener payListener) {
        new AliPay(activity, payListener).startAliPay(param);
    }

    /**
     * 微信支付
     */
    public void payWechat(@NonNull Activity activity, @NonNull WeChatParam weChatParam,
            @NonNull PayListener payListener) {
        new WechatPay(activity, weChatParam.getAppId(), payListener).startWXPay(weChatParam);
    }

    /**
     * 支付宝、微信支付
     */
    public void pay(Activity activity, WebPayInfo webPayInfo, PayListener payListener) {
        String type = webPayInfo.getPayType();
        if (WebPayMode.ALI_PAY.equals(type)) {
            payAli(activity, webPayInfo.getPayInfo(), payListener);
        } else if (WebPayMode.WECHAT_PAY.equals(type)) {
            try {
                JSONObject jsonObject = new JSONObject(webPayInfo.getPayInfo());
                WeChatParam weChatParam = new WeChatParam();
                weChatParam.setAppId(jsonObject.getString("appId"));
                weChatParam.setPartnerId(jsonObject.getString("partnerid"));
                weChatParam.setNonceStr(jsonObject.getString("nonceStr"));
                weChatParam.setPrepayId(jsonObject.getString("prepayid"));
                weChatParam.setTimeStamp(jsonObject.getString("timeStamp"));
                weChatParam.setSign(jsonObject.getString("sign"));
                weChatParam.setPackageValue(jsonObject.getString("package"));
                payWechat(activity, weChatParam, payListener);
            } catch (JSONException e) {
                payListener.onPayError(0, "支付格式错误");
                e.printStackTrace();
            }
        }
    }
}
