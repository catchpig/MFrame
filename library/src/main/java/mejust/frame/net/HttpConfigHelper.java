package mejust.frame.net;

import java.util.concurrent.TimeUnit;
import mejust.frame.annotation.ServiceUrl;
import mejust.frame.app.AppConfig;
import mejust.frame.utils.AnnotationUtils;
import mejust.frame.utils.JsonUtil;
import mejust.frame.utils.log.Logger;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:03
 * <p>
 * HTTP请求配置生成类
 */
public class HttpConfigHelper {

    /**
     * Retrofit生成Api配置
     *
     * @param cls Api类
     * @param client OkHttpClient
     * @param <S> Api泛型类
     * @return 请求Api
     */
    public <S> S createApi(Class<S> cls, OkHttpClient client) {
        // 注解获取baseUrl
        ServiceUrl url = AnnotationUtils.annotationRecycle(cls, ServiceUrl.class);
        if (url == null) {
            throw new IllegalArgumentException("Api must set baseUrl,@Url");
        }
        return buildRetrofit(url.value(), client).create(cls);
    }

    /**
     * 生成请求的Retrofit
     *
     * @param baseUrl 基础url
     * @param client OkHttpClient
     * @return Retrofit
     */
    public Retrofit buildRetrofit(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(JsonUtil.getGson()))
                .client(client)
                .build();
    }

    /**
     * 生成的默认OkHttpClient.Builder,配置常用的默认操作
     * 可build()生成，或添加修改参数
     *
     * @return OkHttpClient.Builder
     */
    public OkHttpClient.Builder buildDefaultOkHttpClientBuilder() {
        return new OkHttpClient.Builder().connectTimeout(AppConfig.CONNECT_TIME_OUT_DEFAULT,
                TimeUnit.SECONDS)
                .readTimeout(AppConfig.READ_TIME_OUT_DEFAULT, TimeUnit.SECONDS)
                .writeTimeout(AppConfig.WRITE_TIME_OUT_DEFAULT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
    }

    /**
     * http请求日志拦截器
     *
     * @return HttpLoggingInterceptor
     */
    public HttpLoggingInterceptor createHttpLogInterceptor() {
        HttpLoggingInterceptor interceptor =
                new HttpLoggingInterceptor(message -> Logger.d(AppConfig.URL_LOG, message));
        interceptor.setLevel(AppConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }
}
