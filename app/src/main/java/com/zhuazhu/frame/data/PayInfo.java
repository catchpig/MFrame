package com.zhuazhu.frame.data;

/**
 * 创建时间: 2018/04/16
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/16
 * 描述: <empty/>
 */
public class PayInfo {

    private String redirectUrl;

    private String payType;

    private String payInfo;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }
}
