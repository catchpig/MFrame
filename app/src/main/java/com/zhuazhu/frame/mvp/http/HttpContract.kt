package com.zhuazhu.frame.mvp.http

import mejust.frame.mvp.BaseContract

/**
 * @author wangpeifeng
 * @date 2018/06/04 15:43
 */
interface HttpView : BaseContract.View {

    fun showResult(ss: String)
}

interface HttpPresenter : BaseContract.Presenter {

    fun doGet()
}