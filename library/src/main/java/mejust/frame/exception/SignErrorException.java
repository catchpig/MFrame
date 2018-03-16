package mejust.frame.exception;

import mejust.frame.utils.log.Logger;

/**
 * 创建时间:2018-02-08 16:15<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2018-02-08 16:15<br/>
 * 描述:
 */

public class SignErrorException extends Exception {
    private static final String TAG = "SignErrorException";
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;

    public SignErrorException(int code) {
        this.code = code;
        this.message = "验签失败";
        Logger.e(TAG, code + ":" + message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SignErrorException{" + "code=" + code + ", message='" + message + '\'' + '}';
    }
}
