package com.zhuazhu.frame.app

import android.app.Application
import conm.zhuazhu.common.utils.Utils
import mejust.frame.refactor.FrameManager
import mejust.frame.refactor.config.FrameConfig
import mejust.frame.refactor.image.ImageConfig
import mejust.frame.refactor.net.NetConfig

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
        val imageConfig = ImageConfig()
        val netConfig = NetConfig()
        val frameConfig = FrameConfig()
        FrameManager.init(this, imageConfig, netConfig, frameConfig)
    }
}
