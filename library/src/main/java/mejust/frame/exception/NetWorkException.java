package mejust.frame.exception;

import mejust.frame.net.NetWorkCode;

/**
 * 创建时间: 2018/03/21 17:19<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/21 17:19<br>
 * 描述:
 */
public class NetWorkException {

    public static class HttpError extends BaseException {

        public HttpError(int errorCode, String errorMessage) {
            super(errorCode, errorMessage);
        }
    }

    public static class TokenError extends BaseException {

        public TokenError() {
            super(NetWorkCode.TOKEN_ERROR, "请求Token失效");
        }
    }

    public static class SignError extends BaseException {

        public SignError() {
            super(NetWorkCode.SIGN_ERROR, "请求验签失败");
        }
    }
}
