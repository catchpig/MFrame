package com.zhuazhu.frame.di.component

import com.zhuazhu.frame.di.module.AppModule
import com.zhuazhu.frame.di.module.HttpModule
import dagger.BindsInstance
import dagger.Component
import mejust.frame.di.component.FrameComponent
import javax.inject.Singleton

/**
 * @author wangpeifeng
 * @date 2018/06/04 15:38
 */
@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun httpComponent(httpModule: HttpModule): HttpComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun frameComponent(frameComponent: FrameComponent): Builder

        fun build(): AppComponent
    }
}