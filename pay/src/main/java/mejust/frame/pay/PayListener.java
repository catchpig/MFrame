package mejust.frame.pay;

/**
 * 创建时间: 2018/04/13
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/13
 * 描述: <empty/>
 */
public interface PayListener {

    //支付成功
    void onPaySuccess();

    //支付失败
    void onPayError(int error_code, String message);

    //支付取消
    void onPayCancel();
}
