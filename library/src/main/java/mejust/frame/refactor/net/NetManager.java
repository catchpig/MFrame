package mejust.frame.refactor.net;

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
import mejust.frame.mvp.view.support.ActivityHandler;
import mejust.frame.refactor.config.FrameConfig;
import mejust.frame.refactor.net.config.IHttpResult;
import mejust.frame.refactor.net.config.NetConfig;
import mejust.frame.refactor.net.error.NetWorkException;
import mejust.frame.utils.AnnotationUtils;
import mejust.frame.utils.CommonUtil;
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

    public <T> ObservableTransformer<IHttpResult<T>, T> transformerResult() {
        return upstream -> upstream.flatMap(
                (Function<IHttpResult<T>, ObservableSource<T>>) tiHttpResult -> {
                    String statusCode = tiHttpResult.getStatusCode();
                    if (netConfig.getResponseCodeSuccess().equals(statusCode)) {
                        T data = tiHttpResult.getResultData();
                        return data == null ? Observable.empty() : Observable.just(data);
                    }
                    if (netConfig.getResponseCodeTokenError().equals(statusCode)) {
                        CommonUtil.startLoginActivity(frameConfig.getLoginClass());
                        return Observable.error(new NetWorkException.TokenError(statusCode));
                    }
                    String errorMsg = netConfig.getResponseErrorMap().get(statusCode);
                    if (TextUtils.isEmpty(errorMsg)) {
                        errorMsg = tiHttpResult.getStatusMessage();
                    }
                    return Observable.error(new NetWorkException.HttpError(statusCode, errorMsg));
                }).subscribeOn(Schedulers.io());
    }

    public <T> ObservableTransformer<IHttpResult<T>, T> transformerHttp() {
        return upstream -> upstream.compose(transformerResult()).compose(handleScheduler());
    }

    public <T> ObservableTransformer<IHttpResult<T>, T> transformerHttp(
            ObservableSource<? extends T> emptyOther) {
        if (emptyOther == null) {
            return transformerHttp();
        } else {
            return upstream -> upstream.compose(transformerHttp()).switchIfEmpty(emptyOther);
        }
    }

    public <T> ObservableTransformer<T, T> handleLoadView(final Handler handler) {
        return handleLoadView(handler, true);
    }

    public <T> ObservableTransformer<T, T> handleLoadView(final Handler handler, boolean isDialog) {
        return upstream -> upstream.doOnSubscribe(disposable -> {
            int what = isDialog ? ActivityHandler.HANDLER_MSG_LOADING_DIALOG_OPEN
                    : ActivityHandler.HANDLER_MSG_LOADING_VIEW_OPEN;
            handler.sendEmptyMessage(what);
        })
                .doOnTerminate(() -> handler.sendEmptyMessageDelayed(
                        ActivityHandler.HANDLER_MSG_LOADING_CLOSE, 3000));
    }

    private Retrofit.Builder retrofitBuild() {
        return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setDateFormat(netConfig.getDateFormat()).create()))
                .client(okHttpClient);
    }
}
