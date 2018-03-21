package mejust.frame.net;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import mejust.frame.enums.NetWorkCode;
import mejust.frame.exception.NetWorkException;

/**
 * 创建时间:2017/12/21 20:40<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/21 20:40<br/>
 * 描述: 回调Flowable的结果处理工具类
 */

public class FlowableUtils {

    /** 统一的线程切换处理 */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 转换请求结果，统一进行错误处理
     *
     * @return 结果
     */
    public static <T> FlowableTransformer<AjaxResult<T>, Optional<T>> transformerResult() {
        return httpResponseFollowable -> httpResponseFollowable.flatMap(
                (Function<AjaxResult<T>, Flowable<Optional<T>>>) result -> {
                    @NetWorkCode int code = result.getCode();
                    Exception exception;
                    switch (code) {
                        case NetWorkCode.SUCCESS:
                            return Flowable.just(result.getData());
                        case NetWorkCode.TOKEN_ERROR:
                            exception = new NetWorkException.TokenError();
                            break;
                        case NetWorkCode.SIGN_ERROR:
                            exception = new NetWorkException.SignError();
                            break;
                        default:
                            exception = new NetWorkException.HttpError(code, result.getMessage());
                            break;
                    }
                    return Flowable.error(exception);
                });
    }

    /**
     * 处理接口回调的数据和将接口请求切换到主线程
     *
     * @param <T> 回调成功数据的实体泛型
     */
    public static <T> Flowable<Optional<T>> create(Flowable<AjaxResult<T>> flowable) {
        return flowable.compose(transformerResult()).compose(rxSchedulerHelper());
    }
}
