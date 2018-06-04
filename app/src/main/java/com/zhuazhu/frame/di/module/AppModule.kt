package com.zhuazhu.frame.di.module

import com.zhuazhu.frame.data.ApiOne
import dagger.Module
import dagger.Provides
import mejust.frame.di.component.FrameComponent
import javax.inject.Singleton

/**
 * @author wangpeifeng
 * @date 2018/06/04 15:32
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApiOne(frameComponent: FrameComponent): ApiOne {
        return frameComponent.netManager().getApi(ApiOne::class.java)
    }

}