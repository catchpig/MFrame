package com.zhuazhu.frame.di.component;

import com.zhuazhu.frame.di.module.RecyclerModuleBase;
import com.zhuazhu.frame.mvp.activity.RecyclerActivity;

import dagger.Subcomponent;
import mejust.frame.di.scope.ActivityScope;

/**
 * Created by Beaven on 2017/12/22.
 */
@ActivityScope
@Subcomponent(modules = RecyclerModuleBase.class)
public interface RecyclerComponent {
    void inject(RecyclerActivity activity);
}
