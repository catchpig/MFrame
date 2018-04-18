package com.zhuazhu.frame.di.component;

import com.zhuazhu.frame.di.module.MainModuleBase;
import com.zhuazhu.frame.mvp.activity.MainActivity;

import dagger.Subcomponent;
import mejust.frame.di.scope.ActivityScope;

/**
 * @author : Beaven
 * @date : 2017-12-20 12:49
 */
@ActivityScope
@Subcomponent(modules = MainModuleBase.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
