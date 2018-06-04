package mejust.frame.di.module;

import dagger.Module;
import dagger.Provides;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import mejust.frame.common.json.GsonManagerImpl;
import mejust.frame.common.json.IJsonManager;
import mejust.frame.data.FrameConfig;
import mejust.frame.common.image.GlideLoadManager;
import mejust.frame.common.image.IImageLoadManager;
import mejust.frame.common.image.ImageConfig;
import mejust.frame.net.NetManager;
import mejust.frame.net.config.NetConfig;
import mejust.frame.common.log.Logger;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author wangpeifeng
 * @date 2018/05/31 10:32
 */
@Module
public class FrameModule {

    @Singleton
    @Provides
    public IImageLoadManager provideImageLoadManager(ImageConfig imageConfig) {
        return new GlideLoadManager(imageConfig);
    }

    @Singleton
    @Provides
    public NetManager provideNetManager(FrameConfig frameConfig, NetConfig netConfig,
            OkHttpClient okHttpClient) {
        return new NetManager(frameConfig, netConfig, okHttpClient);
    }

    @Singleton
    @Provides
    public IJsonManager provideJsonManager(NetConfig netConfig) {
        return new GsonManagerImpl(netConfig);
    }

    @Singleton
    @Provides
    public OkHttpClient provideHttpClient(NetConfig netConfig, FrameConfig frameConfig) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        List<Interceptor> interceptorList = netConfig.getHttpInterceptor();
        for (Interceptor interceptor : interceptorList) {
            builder.addInterceptor(interceptor);
        }
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> Logger.d(netConfig.getHttpLogTag(), message));
        httpLoggingInterceptor.setLevel(frameConfig.isDebug() ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.connectTimeout(netConfig.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(netConfig.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(netConfig.getReadTimeout(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }
}
