package com.zhuazhu.frame.mvp.main.model

import android.app.Activity

/**
 * @author wangpeifeng
 * @date 2018/05/31 14:07
 */
data class PageItem(val name: String, val aClass: Class<out Activity>)
