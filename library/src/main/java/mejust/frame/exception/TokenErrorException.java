package mejust.frame.exception;

import mejust.frame.utils.log.Logger;

/**
 * 创建时间:2017/12/21 19:56<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/21 19:56<br/>
 * 描述:token失效
 */

public class TokenErrorException extends Exception {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;
    public TokenErrorException(int code){
        this.code = code;
        this.message = "token失效";
        Logger.e(this,code+":"+message);
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
        return "TokenErrorException{" + "code=" + code + ", message='" + message + '\'' + '}';
    }
}
