package com.zhuazhu.frame.mvp.http.view

import com.zhuazhu.frame.R
import com.zhuazhu.frame.app.FrameApplication
import com.zhuazhu.frame.di.module.HttpModule
import com.zhuazhu.frame.mvp.http.HttpView
import com.zhuazhu.frame.mvp.http.presenter.HttpPresenter
import kotlinx.android.synthetic.main.activity_http.bt_get
import kotlinx.android.synthetic.main.activity_http.bt_test
import kotlinx.android.synthetic.main.activity_http.text_http_result
import mejust.frame.annotation.LayoutId
import mejust.frame.annotation.TitleBarConfig
import mejust.frame.common.log.Logger
import mejust.frame.mvp.view.BasePresenterActivity

@TitleBarConfig(textValue = "Http")
@LayoutId(R.layout.activity_http)
class HttpActivity : BasePresenterActivity<HttpPresenter>(), HttpView {

    override fun initParam() {
    }

    override fun injectComponent() {
        FrameApplication.appComponent.httpComponent(HttpModule(this)).inject(this)
    }

    override fun initView() {
        bt_get.setOnClickListener {
            mPresenter.doGet()
        }
        bt_test.setOnClickListener {
            Logger.i("bt_test click")
        }
    }

    override fun showResult(ss: String) {
        text_http_result.text = ss
    }
}
