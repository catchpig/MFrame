package mejust.frame.net;

import android.support.annotation.Nullable;
import io.reactivex.subscribers.ResourceSubscriber;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import mejust.frame.exception.NetWorkException;
import mejust.frame.mvp.BaseContract;
import mejust.frame.utils.log.Logger;

/**
 * 创建时间:2017/12/21 19:59<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/21 19:59<br/>
 * 描述:接口回调函数
 */

public abstract class Callback<T> extends ResourceSubscriber<Optional<T>> {

    /**
     * loading类型
     */
    public enum Type {
        /**
         * 不展示
         */
        LOADING_NO,
        /**
         * 展示dialog
         */
        LOADING_DIALOG,
        /*
         * 展示view
         */
        LOADING_VIEW
    }

    private BaseContract.View mView;
    private Type mType = Type.LOADING_DIALOG;

    public Callback() {
        mType = Type.LOADING_NO;
    }

    public Callback(BaseContract.View view) {
        mView = view;
    }

    public Callback(BaseContract.View view, Type type) {
        mView = view;
        mType = type;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mView != null) {
            switch (mType) {
                case LOADING_NO:
                    break;
                case LOADING_DIALOG:
                    mView.showLoading(true);
                    break;
                case LOADING_VIEW:
                    mView.showLoading(false);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNext(Optional<T> optional) {
        if (optional.isEmpty()) {
            success(null);
        } else {
            success(optional.get());
        }
    }

    protected abstract void success(@Nullable T t);

    @Override
    public void onError(Throwable t) {
        String msg;
        if (t instanceof NetWorkException.TokenError) {
            //token失效
            NetWorkException.TokenError e = (NetWorkException.TokenError) t;
            msg = e.getErrorMessage();
            if (mView != null) {
                mView.startLoginActivity();
            }
        } else if (t instanceof NetWorkException.SignError) {
            //验签失败
            NetWorkException.SignError e = (NetWorkException.SignError) t;
            msg = e.getErrorMessage();
        } else if (t instanceof NetWorkException.HttpError) {
            NetWorkException.HttpError e = (NetWorkException.HttpError) t;
            msg = e.getErrorMessage();
        } else if (t instanceof ConnectException
                || t instanceof SocketTimeoutException
                || t instanceof UnknownHostException
                || t instanceof UnknownServiceException) {
            msg = "连接超时,请检查网络";
        } else if (t instanceof NullPointerException) {
            msg = "空指针异常";
        } else {
            msg = "未知异常";
        }
        Logger.e(msg, t);
        if (mView != null) {
            mView.showToast(msg);
        }
        onComplete();
    }

    @Override
    public void onComplete() {
        if (mView != null) {
            mView.hideLoading();
        }
    }
}
