package com.zhuazhu.frame.di.component;

import com.zhuazhu.frame.di.module.RecyclerModule;
import com.zhuazhu.frame.mvp.activity.RecyclerActivity;

import dagger.Subcomponent;

/**
 * Created by Beaven on 2017/12/22.
 */
@Subcomponent(modules = RecyclerModule.class)
public interface RecyclerComponent {
    void inject(RecyclerActivity activity);
}
