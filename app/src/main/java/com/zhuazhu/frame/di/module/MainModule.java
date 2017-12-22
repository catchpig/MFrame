package com.zhuazhu.frame.di.module;

import com.zhuazhu.frame.mvp.contract.MainContract;
import dagger.Module;
import dagger.Provides;
import mejust.frame.annotation.ActivityScope;

/**
 * @author : Beaven
 * @date : 2017-12-20 12:48
 */
@Module
public class MainModule {

    private final MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public MainContract.View provideMainView() {
        return view;
    }
}
