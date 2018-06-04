package com.zhuazhu.frame.data

import com.zhuazhu.frame.mvp.http.model.ResultOne
import io.reactivex.Observable
import mejust.frame.annotation.ServiceUrl
import retrofit2.http.GET

/**
 * @author wangpeifeng
 * @date 2018/06/01 9:43
 */
@ServiceUrl("http://wanandroid.com")
interface ApiOne {

    @GET("/tools/mockapi/6193/1243one")
    fun requestOne(): Observable<HttpResult<ResultOne>>

}