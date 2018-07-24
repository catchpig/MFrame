package mejust.frame.net.config;

import android.app.Activity;
import android.content.Intent;
import conm.zhuazhu.common.utils.Utils;
import java.util.Locale;
import mejust.frame.data.exception.BaseException;
import mejust.frame.widget.ToastFrame;

/**
 * @author wangpeifeng
 * @date 2018/06/01 15:30
 */
public class NetWorkException {

    public static class HttpError extends BaseException {

        public HttpError(String errorCode, String errorMessage) {
            super(errorCode, errorMessage);
        }

        @Override
        public void handleException() {
            super.handleException();
            String data =
                    String.format(Locale.getDefault(), "%s:%s", getErrorCode(), getErrorMessage());
            ToastFrame.show(data);
        }
    }

    public static class TokenError extends BaseException {

        private final Class<? extends Activity> loginClass;

        public TokenError(String errorCode, Class<? extends Activity> loginClass) {
            super(errorCode, "登录过期，需重新登录");
            this.loginClass = loginClass;
        }

        @Override
        public void handleException() {
            super.handleException();
            if (loginClass == null) {
                return;
            }
            Intent intent = new Intent(Utils.getApp(), loginClass);
            Utils.getTopActivity().startActivity(intent);
        }
    }
}
