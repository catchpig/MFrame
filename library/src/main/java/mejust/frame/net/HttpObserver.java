package mejust.frame.net;

import android.text.TextUtils;
import io.reactivex.observers.ResourceObserver;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import mejust.frame.net.config.NetWorkException;
import mejust.frame.common.log.Logger;
import mejust.frame.widget.ToastFrame;

/**
 * @author wangpeifeng
 * @date 2018/06/04 11:08
 */
public abstract class HttpObserver<T> extends ResourceObserver<T> {

    @Override
    public void onError(Throwable e) {
        if (isNetworkStatusError(e)) {
            ToastFrame.show("网络连接异常，请检查网络重试");
        } else if (e instanceof NetWorkException.TokenError) {
            ((NetWorkException.TokenError) e).handleException();
        } else {
            handleError(e);
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 检查是否是网络连接异常
     */
    private boolean isNetworkStatusError(Throwable t) {
        return t instanceof ConnectException
                || t instanceof SocketTimeoutException
                || t instanceof UnknownHostException
                || t instanceof UnknownServiceException;
    }

    /**
     * 处理自定义异常
     */
    protected void handleError(Throwable t) {
        if (t instanceof NetWorkException.HttpError) {
            ((NetWorkException.HttpError) t).handleException();
        } else {
            String errorMessage = t.getMessage();
            if (TextUtils.isEmpty(t.getMessage())) {
                errorMessage = "未定义错误";
            }
            ToastFrame.show(errorMessage);
            Logger.e(t, "未定义错误");
        }
    }
}
