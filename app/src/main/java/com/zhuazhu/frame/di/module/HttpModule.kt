package com.zhuazhu.frame.di.module

import com.zhuazhu.frame.data.ApiTwo
import com.zhuazhu.frame.mvp.http.HttpView
import dagger.Module
import dagger.Provides
import mejust.frame.di.component.FrameComponent
import mejust.frame.di.module.BaseActivityModule
import mejust.frame.di.scope.ActivityScope

/**
 * @author wangpeifeng
 * @date 2018/06/04 15:37
 */
@Module
class HttpModule(view: HttpView) : BaseActivityModule<HttpView>(view) {

    @Provides
    @ActivityScope
    fun provideApiTwo(frameComponent: FrameComponent): ApiTwo {
        return frameComponent.netManager().getApi(ApiTwo::class.java)
    }
}