package mejust.frame.refactor.di;

import dagger.Module;
import dagger.Provides;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import mejust.frame.refactor.config.FrameConfig;
import mejust.frame.refactor.image.GlideLoadManager;
import mejust.frame.refactor.image.IImageLoadManager;
import mejust.frame.refactor.image.ImageConfig;
import mejust.frame.refactor.net.NetManager;
import mejust.frame.refactor.net.config.NetConfig;
import mejust.frame.utils.log.Logger;
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
