package com.zhuazhu.frame.di.component

import com.zhuazhu.frame.di.module.HttpModule
import com.zhuazhu.frame.mvp.http.view.HttpActivity
import dagger.Subcomponent
import mejust.frame.di.scope.ActivityScope

/**
 * @author wangpeifeng
 * @date 2018/06/04 15:41
 */
@ActivityScope
@Subcomponent(modules = [HttpModule::class])
interface HttpComponent {

    fun inject(activity: HttpActivity)
}