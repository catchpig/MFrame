package com.zhuazhu.frame.mvp.http

import android.os.Bundle
import com.zhuazhu.frame.R
import com.zhuazhu.frame.data.ApiService
import com.zhuazhu.frame.mvp.http.model.ResultOne
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_http.bt_get
import kotlinx.android.synthetic.main.activity_http.bt_test
import kotlinx.android.synthetic.main.activity_http.text_http_result
import mejust.frame.annotation.LayoutId
import mejust.frame.annotation.TitleBarConfig
import mejust.frame.mvp.view.BaseActivity
import mejust.frame.refactor.FrameManager
import mejust.frame.refactor.net.HttpObserver
import mejust.frame.refactor.net.error.NetWorkException

@TitleBarConfig(textValue = "Http")
@LayoutId(R.layout.activity_http)
class HttpActivity : BaseActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bt_get.setOnClickListener {
            val disposable = FrameManager.netManager().getApi(ApiService::class.java).requestOne()
                .compose(FrameManager.netManager().handleLoadView(handler))
                .compose(FrameManager.netManager().transformerHttp())
                .subscribeWith(object : HttpObserver<ResultOne>() {
                    override fun onNext(t: ResultOne) {
                        text_http_result.text = t.toString()
                    }

                    override fun handleError(t: Throwable?) {
                        super.handleError(t)
                        t?.let {
                            if (it is NetWorkException.HttpError) {
                                val code = it.errorCode
                                if (code == "404") {
                                    text_http_result.text = it.errorMessage
                                }
                            }
                        }
                    }
                })
            compositeDisposable.add(disposable)
        }
        bt_test.setOnClickListener {
            showLoading(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
