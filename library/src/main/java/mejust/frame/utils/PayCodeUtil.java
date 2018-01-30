package mejust.frame.utils;

import java.util.HashMap;
import mejust.frame.annotation.PayCodeType;

/**
 * 创建时间: 2018/01/30 15:18<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2018/01/30 15:18<br/>
 * 描述: 校验付款码的来源，是否符合规范
 */
public class PayCodeUtil {

    private static HashMap<String, String> map = new HashMap<>();

    static {
        map.put("10", PayCodeType.WE_PAY);
        map.put("11", PayCodeType.WE_PAY);
        map.put("12", PayCodeType.WE_PAY);
        map.put("13", PayCodeType.WE_PAY);
        map.put("14", PayCodeType.WE_PAY);
        map.put("15", PayCodeType.WE_PAY);
        map.put("25", PayCodeType.AL_PAY);
        map.put("26", PayCodeType.AL_PAY);
        map.put("27", PayCodeType.AL_PAY);
        map.put("28", PayCodeType.AL_PAY);
        map.put("29", PayCodeType.AL_PAY);
        map.put("30", PayCodeType.AL_PAY);
    }

    /**
     * 支付宝付款码：25-30开头，长度为16-24位
     * 微信付款码：18位纯数字，以10、11、12、13、14、15开头
     *
     * @param payCode 付款码，当前只能较远支付宝和微信付款码
     * @return 支付类型 {@link PayCodeType}
     */
    @PayCodeType
    public static String checkPayCodeType(String payCode) {
        if (payCode == null || payCode.length() < 2) {
            return null;
        }
        String headNumber = payCode.substring(0, 2);
        String payType = map.get(headNumber);
        if (payType == null) {
            return null;
        }
        int length = payCode.length();
        if (payType.equals(PayCodeType.AL_PAY)) {
            if (length > 15 && length < 25) {
                return PayCodeType.AL_PAY;
            }
        } else if (payType.equals(PayCodeType.WE_PAY)) {
            if (length == 18) {
                return PayCodeType.WE_PAY;
            }
        }
        return null;
    }
}
