package mejust.frame.pay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.app.PayTask;
import java.util.Map;
import mejust.frame.pay.PayListener;

/**
 * 创建时间: 2018/04/13
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/13
 * 描述: <empty/>
 */
public class AliPay {

    public static final int SDK_PAY_FLAG = 1;
    //支付失败
    public static final int PAY_ERROR = 0x001;
    //支付网络连接出错
    public static final int PAY_NETWORK_ERROR = 0x002;
    //正在处理中
    public static final int PAY_DEALING = 0x004;
    //其它支付错误
    public static final int PAY_OTHER_ERROR = 0x006;

    private static PayListener payListener;

    private Activity context;

    public AliPay(Activity context, PayListener payListener) {
        this.context = context;
        AliPay.payListener = payListener;
    }

    public void startAliPay(final String orderInfo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = AliPay.SDK_PAY_FLAG;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    private static Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SDK_PAY_FLAG) {
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                Log.d("aliPay call ", payResult.toString());
                if (AliPay.payListener == null) {
                    Log.e("aliPay call", "handleMessage: == payListener is null");
                    return;
                }
                PayListener payListener = AliPay.payListener;
                // 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                String resultStatus = payResult.getResultStatus();
                if (TextUtils.equals(resultStatus, "9000")) {
                    //支付成功
                    payListener.onPaySuccess();
                } else if (TextUtils.equals(resultStatus, "8000")) {
                    //正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                    payListener.onPayError(PAY_DEALING, "正在处理结果中");
                } else if (TextUtils.equals(resultStatus, "6001")) {
                    //支付取消
                    payListener.onPayCancel();
                } else if (TextUtils.equals(resultStatus, "6002")) {
                    //网络连接出错
                    payListener.onPayError(PAY_NETWORK_ERROR, "网络连接出错");
                } else if (TextUtils.equals(resultStatus, "4000")) {
                    //支付错误
                    payListener.onPayError(PAY_ERROR, "订单支付失败");
                } else {
                    payListener.onPayError(PAY_OTHER_ERROR, resultStatus);
                }
            }
            AliPay.payListener = null;
        }
    };
}
