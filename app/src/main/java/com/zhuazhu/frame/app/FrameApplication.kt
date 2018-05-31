package com.zhuazhu.frame.app

import android.app.Application
import com.zhuazhu.frame.BuildConfig
import conm.zhuazhu.common.utils.Utils
import mejust.frame.refactor.FrameManager
import mejust.frame.refactor.config.FrameConfig
import mejust.frame.refactor.image.ImageConfig
import mejust.frame.refactor.net.config.NetConfig

/**
 * @author wangpeifeng
 * @date 2018/05/31 13:44
 */
class FrameApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        initLibrary()
    }

    private fun initLibrary() {
        val imageConfig = ImageConfig().apply {
            hostUrl = BuildConfig.IMAGE_URL
        }
        val netConfig = NetConfig().apply {
            setResponseErrorMessage("2001", "请求验证失败")
        }
        val frameConfig = FrameConfig().apply {
            isDebug = BuildConfig.DEBUG
            isOpenNetworkState = false
        }
        FrameManager.init(this, imageConfig, netConfig, frameConfig)
    }
}
