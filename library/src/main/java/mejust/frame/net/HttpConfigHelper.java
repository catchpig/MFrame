package mejust.frame.net;

import java.lang.reflect.Field;
import mejust.frame.app.AppConfig;
import mejust.frame.utils.JsonUtil;
import okhttp3.OkHttpClient;
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
        String baseUrl = "";
        try {
            // 反射获取Api类中定义的“baseUrl”字段
            Field field = cls.getField(AppConfig.BASE_URL);
            baseUrl = (String) field.get(cls);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return buildRetrofit(baseUrl, client).create(cls);
    }

    /**
     * 生成请求的Retrofit
     *
     * @param baseUrl 基础url
     * @param client OkHttpClient
     * @return Retrofit
     */
    private Retrofit buildRetrofit(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(JsonUtil.getGson()))
                .client(client)
                .build();
    }
}
