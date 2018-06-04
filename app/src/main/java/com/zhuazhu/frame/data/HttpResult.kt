package com.zhuazhu.frame.data

import mejust.frame.net.config.IHttpResult

/**
 * @author wangpeifeng
 * @date 2018/06/01 9:52
 */
data class HttpResult<T>(var data: T, var code: String, var message: String) : IHttpResult<T> {

    override fun getResultData(): T {
        return data
    }

    override fun getResultCode(): String {
        return code
    }

    override fun getResultMessage(): String {
        return message
    }
}