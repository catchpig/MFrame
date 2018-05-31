package mejust.frame.refactor.net;

import java.util.HashMap;

/**
 * @author wangpeifeng
 * @date 2018/05/30 14:47
 */
public class NetConfig {

    public static final String RESULT_CODE_KEY = "result_code_key";
    public static final String RESULT_MSG_KEY = "result_msg_key";
    public static final String RESULT_DATA_KEY = "result_data_key";

    /** http请求log tag */
    private String httpLogTag;
    /** 连接超时,单位：秒 */
    private long connectTimeout;
    /** 读超时,单位：秒 */
    private long readTimeout;
    /** 写超时,单位：秒 */
    private long writeTimeout;
    /** 时间格式配置，用于网络转换 */
    private String dateFormat;
    /** 网络请求成功Code */
    private String responseCodeSuccess;
    /** 网络请求Token失效，跳转登录界面 */
    private String responseCodeTokenError;
    /** 网络请求状态码定义 */
    private HashMap<String, String> responseErrorMap;
    /** 网络返回外层result key定义 */
    private HashMap<String, String> responseResultMap;

    public NetConfig() {
        this.httpLogTag = "HTTP_TAG";
        this.connectTimeout = 10;
        this.readTimeout = 10;
        this.writeTimeout = 10;
        this.dateFormat = "yyyy-MM-dd HH:mm:ss";
        this.responseCodeSuccess = "200";
        this.responseCodeTokenError = "405";
        this.responseErrorMap = new HashMap<>();
        this.responseResultMap = new HashMap<>(3);
    }

    public String getHttpLogTag() {
        return httpLogTag;
    }

    public void setHttpLogTag(String httpLogTag) {
        this.httpLogTag = httpLogTag;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getResponseCodeSuccess() {
        return responseCodeSuccess;
    }

    public void setResponseCodeSuccess(String responseCodeSuccess) {
        this.responseCodeSuccess = responseCodeSuccess;
    }

    public String getResponseCodeTokenError() {
        return responseCodeTokenError;
    }

    public void setResponseCodeTokenError(String responseCodeTokenError) {
        this.responseCodeTokenError = responseCodeTokenError;
    }

    public HashMap<String, String> getResponseErrorMap() {
        return responseErrorMap;
    }

    public void setResponseErrorMessage(String errorCode, String errorMessage) {
        this.responseErrorMap.put(errorCode, errorMessage);
    }

    public void setResponseResultKey(String codeKey, String msgKey, String dataKey) {
        this.responseResultMap.put(RESULT_CODE_KEY, codeKey);
        this.responseResultMap.put(RESULT_MSG_KEY, msgKey);
        this.responseResultMap.put(RESULT_DATA_KEY, dataKey);
    }

    public String getResponseResultKey(String key) {
        return this.responseResultMap.get(key);
    }
}
