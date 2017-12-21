package com.zhuazhu.frame.di.component;

import com.zhuazhu.frame.data.HttpHelper;
import com.zhuazhu.frame.di.module.NetModule;
import dagger.Component;
import javax.inject.Singleton;
import mejust.frame.di.AppModule;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:41
 */
@Singleton
@Component(modules = { NetModule.class, AppModule.class })
public interface AppComponent {

    HttpHelper getHttpHelper();
}
