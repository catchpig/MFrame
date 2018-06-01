package mejust.frame.refactor.net.error;

import mejust.frame.utils.log.Logger;

/**
 * @author wangpeifeng
 * @date 2018/06/01 15:26
 */
public abstract class BaseException extends Exception {

    /** 错误码 */
    private String errorCode;
    /** 错误信息 */
    private String errorMessage;

    public BaseException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        Logger.e(this, errorMessage);
    }

    public String getErrorCode() {
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
