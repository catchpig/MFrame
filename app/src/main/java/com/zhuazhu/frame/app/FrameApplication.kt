package com.zhuazhu.frame.app

import android.app.Application
import android.view.View
import com.squareup.leakcanary.LeakCanary
import com.zhuazhu.frame.BuildConfig
import com.zhuazhu.frame.R
import com.zhuazhu.frame.di.component.AppComponent
import com.zhuazhu.frame.di.component.DaggerAppComponent
import com.zhuazhu.frame.mvp.image.ImageActivity
import conm.zhuazhu.common.utils.Utils
import mejust.frame.FrameManager
import mejust.frame.annotation.TitleBarMenuLocation
import mejust.frame.common.image.ImageConfig
import mejust.frame.data.FrameConfig
import mejust.frame.di.component.FrameComponent
import mejust.frame.net.config.NetConfig
import mejust.frame.widget.title.TitleBarConfig
import mejust.frame.widget.title.TitleBarSetting
import mejust.frame.widget.title.TitleBarSetting.TitleMenu

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
        val frameComponent = initLibrary()
        appComponent = DaggerAppComponent.builder().frameComponent(frameComponent).build()
    }

    private fun initLibrary(): FrameComponent {
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
            isOpenNetworkState = true
            loginClass = ImageActivity::class.java
        }
        var titleBarConfig = TitleBarConfig().apply {
            backDrawable = R.drawable.ic_arrow_back_white
            titleTextColor = R.color.c_fff
            menuTextSize = 15f
            titleTextSize = 18f
            backgroundColor = R.color.c_1e81d2
        }
        return FrameManager.init(this, imageConfig, netConfig, frameConfig,titleBarConfig)
    }
}
