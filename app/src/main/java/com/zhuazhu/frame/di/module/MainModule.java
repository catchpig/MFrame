package com.zhuazhu.frame.di.module;

import com.zhuazhu.frame.mvp.contract.MainContract;

import dagger.Module;
import mejust.frame.di.module.ActivityModule;

/**
 * @author : Beaven
 * @date : 2017-12-20 12:48
 */

@Module
public class MainModule extends ActivityModule<MainContract.View> {

    public MainModule(MainContract.View view) {
        super(view);
    }
}
