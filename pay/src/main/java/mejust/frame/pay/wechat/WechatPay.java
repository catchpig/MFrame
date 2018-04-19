package mejust.frame.pay.wechat;

import android.content.Context;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import mejust.frame.pay.PayListener;

/**
 * 创建时间: 2018/04/13
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/13
 * 描述: <empty/>
 */
public class WechatPay {

    //未安装微信或微信版本过低
    public static final int WEIXIN_VERSION_LOW = 0x001;

    public static String APP_ID = "";
    public static PayListener payListener;

    private IWXAPI iwxapi;

    public WechatPay(Context context, String appId, PayListener payListener) {
        WechatPay.payListener = payListener;
        WechatPay.APP_ID = appId;
        iwxapi = WXAPIFactory.createWXAPI(context, APP_ID);
        iwxapi.registerApp(APP_ID);
    }

    public void startWXPay(WeChatParam weChatParam) {
        if (!checkWx()) {
            if (WechatPay.payListener != null) {
                WechatPay.payListener.onPayError(WEIXIN_VERSION_LOW, "未安装微信或者微信版本过低");
            }
            return;
        }
        PayReq request = new PayReq();
        request.appId = weChatParam.getAppId();
        request.partnerId = weChatParam.getPartnerId();
        request.prepayId = weChatParam.getPrepayId();
        request.packageValue = weChatParam.getPackageValue();
        request.nonceStr = weChatParam.getNonceStr();
        request.timeStamp = weChatParam.getTimeStamp();
        request.sign = weChatParam.getSign();
        iwxapi.sendReq(request);
    }

    /**
     * 检测微信客户端是否支持微信支付
     */
    private boolean checkWx() {
        return iwxapi.isWXAppInstalled()
                && iwxapi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
}
