package mejust.frame.refactor.net;

import com.google.gson.GsonBuilder;
import java.util.HashMap;
import mejust.frame.annotation.ServiceUrl;
import mejust.frame.refactor.net.config.NetConfig;
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

    private NetConfig netConfig;
    private Retrofit.Builder retrofitBuilder;
    private HashMap<String, Retrofit> retrofitMap = new HashMap<>();

    public NetManager(NetConfig netConfig, OkHttpClient okHttpClient,
            Retrofit.Builder retrofitBuilder) {
        this.netConfig = netConfig;
        this.retrofitBuilder =
                retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(
                                new GsonBuilder().setDateFormat(netConfig.getDateFormat())
                                        .create()))
                        .client(okHttpClient);
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
            retrofit = this.retrofitBuilder.baseUrl(baseUrl).build();
            retrofitMap.put(baseUrl, retrofit);
        }
        return retrofit.create(sClass);
    }
}
