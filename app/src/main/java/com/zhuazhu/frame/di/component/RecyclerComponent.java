package com.zhuazhu.frame.di.component;

import com.zhuazhu.frame.di.module.RecyclerModule;
import com.zhuazhu.frame.mvp.activity.RecyclerActivity;

import dagger.Subcomponent;
import mejust.frame.annotation.ActivityScope;

/**
 * Created by Beaven on 2017/12/22.
 */
@ActivityScope
@Subcomponent(modules = RecyclerModule.class)
public interface RecyclerComponent {
    void inject(RecyclerActivity activity);
}
