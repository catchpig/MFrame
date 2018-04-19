package mejust.frame.pay.wechat;

/**
 * 创建时间: 2018/04/13
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/13
 * 描述: <empty/>
 */
public class WeChatParam {
    // 应用ID
    private String appId;
    // 商户号
    private String partnerId;
    // 预支付交易会话ID
    private String prepayId;
    // 随机字符串
    private String nonceStr;
    // 时间戳
    private String timeStamp;
    // 签名
    private String sign;

    private String packageValue;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }
}
