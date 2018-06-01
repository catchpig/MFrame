package mejust.frame.refactor.net.error;

/**
 * @author wangpeifeng
 * @date 2018/06/01 15:30
 */
public class NetWorkException {

    public static class HttpError extends BaseException {

        public HttpError(String errorCode, String errorMessage) {
            super(errorCode, errorMessage);
        }
    }

    public static class TokenError extends BaseException {

        public TokenError(String errorCode) {
            super(errorCode, "登录过期，需重新登录");
        }
    }
}
