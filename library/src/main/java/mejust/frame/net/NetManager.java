package mejust.frame.net;

import android.os.Handler;
import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import mejust.frame.annotation.ServiceUrl;
import mejust.frame.mvp.view.BaseActivity;
import mejust.frame.net.config.NetWorkException;
import mejust.frame.config.FrameConfig;
import mejust.frame.net.config.IHttpResult;
import mejust.frame.net.config.NetConfig;
import mejust.frame.utils.AnnotationUtils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wangpeifeng
 * @date 2018/05/31 10:31
 */
public class NetManager {

    private FrameConfig frameConfig;
    private NetConfig netConfig;
    private OkHttpClient okHttpClient;
    private HashMap<String, Retrofit> retrofitMap = new HashMap<>();

    public NetManager(FrameConfig frameConfig, NetConfig netConfig, OkHttpClient okHttpClient) {
        this.frameConfig = frameConfig;
        this.netConfig = netConfig;
        this.okHttpClient = okHttpClient;
    }

    /**
     * ApiService创建
     *
     * @param sClass ApiService.class
     * @return S
     */
    public <S> S getApi(Class<S> sClass) {
        ServiceUrl serviceUrl = AnnotationUtils.annotationRecycle(sClass, ServiceUrl.class);
        if (serviceUrl == null) {
            throw new IllegalArgumentException("ApiService must set baseUrl,@ServiceUrl");
        }
        String baseUrl = serviceUrl.value();
        Retrofit retrofit = retrofitMap.get(baseUrl);
        if (retrofit == null) {
            retrofit = retrofitBuild().baseUrl(baseUrl).build();
            retrofitMap.put(baseUrl, retrofit);
        }
        return retrofit.create(sClass);
    }

    /**
     * 线程切换
     */
    public <T> ObservableTransformer<T, T> handleScheduler() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 结果转换 ， 判断业务结果是否正确，发送正确值，或者发送错误
     *
     * @param <T> 结果bean
     */
    public <T> ObservableTransformer<IHttpResult<T>, T> transformerResult() {
        return upstream -> upstream.flatMap(
                (Function<IHttpResult<T>, ObservableSource<T>>) tiHttpResult -> {
                    String statusCode = tiHttpResult.getResultCode();
                    // 结果正确，默认code == “200”
                    if (netConfig.getResponseCodeSuccess().equals(statusCode)) {
                        T data = tiHttpResult.getResultData();
                        // 结果为null时，配合switchIfEmpty()操作符，设置默认选项
                        return data == null ? Observable.empty() : Observable.just(data);
                    }
                    // token验证错误,默认code=="405",app需重新登录
                    if (netConfig.getResponseCodeTokenError().equals(statusCode)) {
                        return Observable.error(new NetWorkException.TokenError(statusCode,
                                frameConfig.getLoginClass()));
                    }
                    // 其他错误对应信息返回
                    String errorMsg = netConfig.getResponseErrorMap().get(statusCode);
                    if (TextUtils.isEmpty(errorMsg)) {
                        errorMsg = tiHttpResult.getResultMessage();
                    }
                    return Observable.error(new NetWorkException.HttpError(statusCode, errorMsg));
                }).subscribeOn(Schedulers.io());
    }

    /**
     * 结合线程切换和业务结果转换
     */
    public <T> ObservableTransformer<IHttpResult<T>, T> transformerHttp() {
        return upstream -> upstream.compose(transformerResult()).compose(handleScheduler());
    }

    /**
     * 结合线程切换和业务结果转换
     *
     * @param emptyOther 业务结果正确为null时，默认操作设置
     */
    public <T> ObservableTransformer<IHttpResult<T>, T> transformerHttp(
            ObservableSource<? extends T> emptyOther) {
        if (emptyOther == null) {
            return transformerHttp();
        } else {
            return upstream -> upstream.compose(transformerHttp()).switchIfEmpty(emptyOther);
        }
    }

    /**
     * http请求，loading视图控制
     *
     * @param handler Activity handler
     */
    public <T> ObservableTransformer<T, T> handleLoadView(final Handler handler) {
        return handleLoadView(handler, true);
    }

    /**
     * http请求，loading视图控制
     *
     * @param handler Activity handler
     * @param isDialog true 显示dialog，false显示view
     */
    public <T> ObservableTransformer<T, T> handleLoadView(final Handler handler, boolean isDialog) {
        return upstream -> upstream.doOnSubscribe(disposable -> {
            int what = isDialog ? BaseActivity.HANDLER_MSG_LOADING_DIALOG_OPEN
                    : BaseActivity.HANDLER_MSG_LOADING_VIEW_OPEN;
            handler.sendEmptyMessage(what);
        }).doFinally(() -> {
            handler.sendEmptyMessage(BaseActivity.HANDLER_MSG_LOADING_CLOSE);
        });
    }

    /**
     * 基础Retrofit构建
     */
    private Retrofit.Builder retrofitBuild() {
        return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setDateFormat(netConfig.getDateFormat()).create()))
                .client(okHttpClient);
    }
}
