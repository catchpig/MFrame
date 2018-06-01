package com.zhuazhu.frame.mvp.http

import android.os.Bundle
import com.zhuazhu.frame.R
import com.zhuazhu.frame.data.ApiService
import com.zhuazhu.frame.mvp.http.model.ResultOne
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_http.bt_get
import kotlinx.android.synthetic.main.activity_http.bt_test
import mejust.frame.annotation.LayoutId
import mejust.frame.annotation.TitleBarConfig
import mejust.frame.mvp.view.BaseActivity
import mejust.frame.refactor.FrameManager
import mejust.frame.utils.log.Logger

@TitleBarConfig(textValue = "Http")
@LayoutId(R.layout.activity_http)
class HttpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bt_get.setOnClickListener {
            FrameManager.netManager().getApi(ApiService::class.java).requestOne()
                .compose(FrameManager.netManager().handleLoadView(activityHandler))
                .compose(FrameManager.netManager().transformerHttp())
                .subscribe(object : Observer<ResultOne> {
                    override fun onSubscribe(d: Disposable) {
                        Logger.i("request onSubscribe")
                    }

                    override fun onComplete() {
                        Logger.i("request onComplete")
                    }

                    override fun onNext(t: ResultOne) {
                        Logger.i("request onNext--$t")
                    }

                    override fun onError(e: Throwable) {
                        Logger.e(e, "on Error")
                    }
                })
        }
        bt_test.setOnClickListener {
            showLoading(true)
        }
    }
}
