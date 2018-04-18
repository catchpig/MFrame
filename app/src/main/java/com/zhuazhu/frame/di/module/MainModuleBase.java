package com.zhuazhu.frame.di.module;

import com.zhuazhu.frame.mvp.contract.MainContract;

import dagger.Module;
import mejust.frame.di.module.BaseActivityModule;

/**
 * @author : Beaven
 * @date : 2017-12-20 12:48
 */

@Module
public class MainModuleBase extends BaseActivityModule<MainContract.View> {

    public MainModuleBase(MainContract.View view) {
        super(view);
    }
}
