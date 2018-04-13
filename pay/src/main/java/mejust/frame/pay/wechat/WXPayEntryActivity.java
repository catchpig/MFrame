package mejust.frame.pay.wechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import mejust.frame.pay.PayListener;

/**
 * 创建时间: 2018/04/13
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/13
 * 描述: <empty/>
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI iwxapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iwxapi = WXAPIFactory.createWXAPI(this, WechatPay.APP_ID);
        iwxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            onResp(baseResp.errCode, baseResp.errStr);
            Log.e("weiXinPay", "errStr=" + baseResp.errStr);
            finish();
        }
    }

    /**
     * 响应支付回调
     */
    public void onResp(int error_code, String message) {
        PayListener payListener = WechatPay.payListener;
        if (payListener == null) {
            return;
        }
        if (error_code == 0) {
            //支付成功
            payListener.onPaySuccess();
        } else if (error_code == -1) {
            //支付异常
            payListener.onPayError(error_code, message);
        } else if (error_code == -2) {
            //支付取消
            payListener.onPayCancel();
        }
        WechatPay.payListener = null;
    }
}
