package com.zhuazhu.frame.app

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.zhuazhu.frame.BuildConfig
import com.zhuazhu.frame.R
import com.zhuazhu.frame.di.component.AppComponent
import com.zhuazhu.frame.di.component.DaggerAppComponent
import com.zhuazhu.frame.mvp.image.ImageActivity
import mejust.frame.FrameManager
import mejust.frame.config.FrameConfig
import mejust.frame.image.ImageConfig
import mejust.frame.net.config.NetConfig

/**
 * @author wangpeifeng
 * @date 2018/05/31 13:44
 */
class FrameApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        initLibrary()
        appComponent = DaggerAppComponent.builder().frameComponent(FrameManager.getFrameComponent())
            .build()
    }

    private fun initLibrary() {
        val imageConfig = ImageConfig().apply {
            hostUrl = BuildConfig.IMAGE_URL
            placeholderResId = R.drawable.ic_launcher_background
            errorResId = R.drawable.ic_launcher_background
        }
        val netConfig = NetConfig().apply {
            setResponseErrorMessage("2001", "请求验证失败")
            setResponseErrorMessage("404", "404错误，验证")
        }
        val frameConfig = FrameConfig().apply {
            isDebug = BuildConfig.DEBUG
            isOpenNetworkState = false
            loginClass = ImageActivity::class.java
        }
        FrameManager.init(this, imageConfig, netConfig, frameConfig)
    }
}
