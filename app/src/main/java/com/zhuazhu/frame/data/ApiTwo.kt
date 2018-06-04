package com.zhuazhu.frame.data

import com.zhuazhu.frame.mvp.http.model.ResultOne
import io.reactivex.Observable
import mejust.frame.data.annotation.ServiceUrl
import retrofit2.http.GET

/**
 * @author wangpeifeng
 * @date 2018/06/04 15:34
 */
@ServiceUrl("http://wanandroid.com")
interface ApiTwo {

    @GET("/tools/mockapi/6193/1243one")
    fun request(): Observable<HttpResult<ResultOne>>
}