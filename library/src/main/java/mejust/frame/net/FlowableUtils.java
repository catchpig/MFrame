package mejust.frame.net;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import mejust.frame.exception.HttpException;
import mejust.frame.exception.TokenErrorException;

/**
 * 创建时间:2017/12/21 20:40<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/21 20:40<br/>
 * 描述: 回调Flowable的结果处理工具类
 */

public class FlowableUtils {
    private FlowableUtils() {
    }

    public static <T> Flowable<T> successData(final T t) {
        return Flowable.create( emitter -> {
            emitter.onNext(t);
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);
    }
    public static Flowable errorData(Exception e){
        return Flowable.create(emitter -> {
            emitter.onError(e);
            emitter.onComplete();
        },BackpressureStrategy.BUFFER);
    }

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
    public static <T> FlowableTransformer<AjaxResult<T>, T> transformerResult() {
        return httpResponseFollowable -> httpResponseFollowable.flatMap((Function<AjaxResult<T>, Flowable<T>>) result -> {
            int code = result.getCode();
            Exception exception = null;
            switch (code) {
                case 200:
                    return successData(result.getResult());
                case 405:
                    exception = new TokenErrorException(code);
                    break;
                default:
                    exception = new HttpException(code, result.getMsg());
                    break;
            }
            return errorData(exception);
        });
    }

    /**
     * 处理接口回调的数据和将接口请求切换到主线程
     * @param flowable
     * @param <T> 回调成功数据的实体泛型
     * @return
     */
    public static <T> Flowable<T> create(Flowable<AjaxResult<T>> flowable) {
        return flowable.compose(transformerResult()).compose(rxSchedulerHelper());
    }
}
