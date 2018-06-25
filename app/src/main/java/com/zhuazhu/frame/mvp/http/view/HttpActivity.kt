package com.zhuazhu.frame.mvp.http.view

import android.os.Bundle
import butterknife.OnClick
import com.zhuazhu.frame.R
import com.zhuazhu.frame.app.FrameApplication
import com.zhuazhu.frame.di.module.HttpModule
import com.zhuazhu.frame.mvp.http.HttpView
import com.zhuazhu.frame.mvp.http.presenter.HttpPresenter
import kotlinx.android.synthetic.main.activity_http.bt_get
import kotlinx.android.synthetic.main.activity_http.bt_test
import kotlinx.android.synthetic.main.activity_http.text_http_result
import mejust.frame.common.log.Logger
import mejust.frame.data.annotation.Title
import mejust.frame.mvp.view.BasePresenterActivity

//@Title("请求",rightFirstText = "提交",rightSecondText = " 确定",rightFirstDrawable = R.drawable.ic_arrow_back_white)
class HttpActivity : BasePresenterActivity<HttpPresenter>(), HttpView {
    override fun getLayoutId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_http
    }

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
    @OnClick(R.id.rightFirstText)
    fun click1(){
        showToast("")
    }

    override fun showResult(ss: String) {
        text_http_result.text = ss
    }
}
