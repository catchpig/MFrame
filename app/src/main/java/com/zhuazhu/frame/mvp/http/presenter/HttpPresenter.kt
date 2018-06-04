package com.zhuazhu.frame.mvp.http.presenter

import com.zhuazhu.frame.data.ApiOne
import com.zhuazhu.frame.data.ApiTwo
import com.zhuazhu.frame.mvp.http.HttpPresenter
import com.zhuazhu.frame.mvp.http.HttpView
import com.zhuazhu.frame.mvp.http.model.ResultOne
import io.reactivex.Observable
import mejust.frame.FrameManager
import mejust.frame.mvp.presenter.BasePresenter
import mejust.frame.net.HttpObserver
import mejust.frame.net.config.NetWorkException
import javax.inject.Inject

/**
 * @author wangpeifeng
 * @date 2018/06/04 15:56
 */
class HttpPresenter @Inject constructor(
    private val apiOne: ApiOne,
    val apiTwo: ApiTwo,
    view: HttpView
) :
    BasePresenter<HttpView>(view), HttpPresenter {

    override fun doGet() {
        disposableSet.add(
            apiOne.requestOne()
                .compose(FrameManager.netManager().handleLoadView(mView.handler))
                .compose(
                    FrameManager.netManager().transformerHttp(
                        Observable.just(
                            ResultOne(
                                "",
                                ""
                            )
                        )
                    )
                )
                .subscribeWith(object : HttpObserver<ResultOne>() {
                    override fun onNext(t: ResultOne) {
                        mView.showResult(t.toString())
                    }

                    override fun handleError(t: Throwable?) {
                        super.handleError(t)
                        t?.let {
                            if (it is NetWorkException.HttpError) {
                                val code = it.errorCode
                                if (code == "404") {
                                    mView.showResult(it.errorMessage)
                                }
                            }
                        }
                    }
                })
        )
    }

}