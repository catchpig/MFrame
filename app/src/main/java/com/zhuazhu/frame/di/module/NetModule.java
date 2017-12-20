package com.zhuazhu.frame.di.module;

import com.zhuazhu.frame.data.ApiOne;
import com.zhuazhu.frame.data.ApiTwo;
import com.zhuazhu.frame.data.HttpHelper;
import com.zhuazhu.frame.di.scope.ClientOne;
import com.zhuazhu.frame.di.scope.ClientTwo;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import mejust.frame.net.HttpConfigHelper;
import okhttp3.OkHttpClient;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:42
 */
@Module
public class NetModule {

    @Provides
    @ClientOne
    @Singleton
    public OkHttpClient provideOneClient(HttpConfigHelper httpConfigHelper) {
        return httpConfigHelper.buildDefaultOkHttpClientBuilder().build();
    }

    @Provides
    @ClientTwo
    @Singleton
    public OkHttpClient provideTwoClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    public ApiOne provideApiOne(@ClientOne OkHttpClient okHttpClient,
            HttpConfigHelper httpConfigHelper) {
        return httpConfigHelper.createApi(ApiOne.class, okHttpClient);
    }

    @Provides
    @Singleton
    public ApiTwo provideApiTwo(@ClientTwo OkHttpClient okHttpClient,
            HttpConfigHelper httpConfigHelper) {
        return httpConfigHelper.createApi(ApiTwo.class, okHttpClient);
    }

    @Singleton
    @Provides
    public HttpHelper provideHttpHelper(ApiOne apiOne, ApiTwo apiTwo) {
        return new HttpHelper(apiOne, apiTwo);
    }
}
