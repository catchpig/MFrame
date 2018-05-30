package mejust.frame.exception;

import mejust.frame.utils.log.Logger;

/**
 * 创建时间: 2018/03/21 17:11<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/21 17:11<br>
 * 描述:
 */
public abstract class BaseException extends Exception {

    /** 错误码 */
    private int errorCode;
    /** 错误信息 */
    private String errorMessage;

    public BaseException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        Logger.e(this, errorMessage);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "Exception{"
                + "errorCode="
                + errorCode
                + ", errorMessage='"
                + errorMessage
                + '\''
                + '}';
    }
}
