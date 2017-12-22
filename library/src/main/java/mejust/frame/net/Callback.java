package mejust.frame.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import io.reactivex.subscribers.ResourceSubscriber;
import mejust.frame.exception.HttpException;
import mejust.frame.exception.TokenErrorException;
import mejust.frame.mvp.BaseContract;
import mejust.frame.utils.log.Logger;

/**
 * 创建时间:2017/12/21 19:59<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/21 19:59<br/>
 * 描述:接口回调函数
 */

public abstract class Callback<T> extends ResourceSubscriber<T> {
    public static final String TAG = "Callback";
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
        /**
         * 展示view
         */
        LOADING_VIEW;
    }
    private BaseContract.View mView;
    private Type mType = Type.LOADING_DIALOG;
    public Callback(){

    }
    public Callback(BaseContract.View view){
        mView = view;
    }

    public Callback(BaseContract.View view,Type type){
        mView = view;
        mType = type;
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(mView!=null){
            switch (mType){
                case LOADING_NO:
                    break;
                case LOADING_DIALOG:
                    mView.loadingDialog();
                    break;
                case LOADING_VIEW:
                    mView.loadingView();
                    break;
            }
        }
    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    public abstract void success(T t);

    @Override
    public void onError(Throwable t) {
        Logger.e(TAG,t.getMessage());
        String msg = "";
        if(t instanceof TokenErrorException){
            TokenErrorException e = (TokenErrorException) t;
            msg = e.getMessage();
            mView.startLoginActivity();
        }else if(t instanceof HttpException){
            HttpException e = (HttpException) t;
            msg = e.getMessage();
        }else if(t instanceof ConnectException || t instanceof SocketTimeoutException
                || t instanceof UnknownHostException || t instanceof UnknownServiceException){
            msg = "连接超时,请检查网络";
        }else{
            msg = "未知异常";
        }
        mView.show(msg);
    }

    @Override
    public void onComplete() {
        if(mView!=null){
            mView.hidden();
        }
    }
}
