package com.zhuazhu.frame.di.component;

import com.zhuazhu.frame.mvp.activity.MainActivity;
import com.zhuazhu.frame.di.module.MainModule;
import dagger.Component;
import mejust.frame.annotation.ActivityScope;

/**
 * @author : Beaven
 * @date : 2017-12-20 12:49
 */
@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
