package mejust.frame.exception;

import mejust.frame.utils.log.Logger;

/**
 * 创建时间:2017/12/21 20:44<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/21 20:44<br/>
 * 描述:
 */

public class HttpException extends Exception {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;
    public HttpException(int code,String message){
        this.code = code;
        this.message = message;
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
}
