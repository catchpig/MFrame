package com.zhuazhu.frame.mvp.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zhuazhu.frame.R
import com.zhuazhu.frame.mvp.common.model.JsonBean
import kotlinx.android.synthetic.main.activity_common.bt_json_from
import kotlinx.android.synthetic.main.activity_common.bt_json_to
import kotlinx.android.synthetic.main.activity_common.text_result_common
import mejust.frame.FrameManager
import mejust.frame.common.log.Logger
import java.util.Date

class CommonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        bt_json_to.setOnClickListener {
            listToString()
        }
        bt_json_from.setOnClickListener {
            stringToList()
        }
    }

    private fun beanToString() {
        val bean = JsonBean("Hello World", 547487, Date())
        val data = FrameManager.jsonManager().toString(bean)
        text_result_common.text = data
        Logger.i("json to String--$data")
    }

    private fun stringToBean() {
        val string =
            "{\"result\":\"Hello World\",\"date\":\"2018-06-04 14:21:16\",\"code\":547487}"
        val jsonBean =
            FrameManager.jsonManager().fromJson(string, JsonBean::class.java)
        text_result_common.text = jsonBean.toString()
        Logger.i("json to String--$jsonBean")
    }

    private fun listToString() {
        val list = listOf(
            JsonBean("hello", 21, Date()),
            JsonBean("hello", 22, Date()),
            JsonBean("hello", 23, Date())
        )
        val data = FrameManager.jsonManager().toString(list)
        text_result_common.text = data
        Logger.i("json to String--$data")
    }

    private fun stringToList() {
        val string =
            "[{\"result\":\"hello\",\"date\":\"2018-06-04 15:26:32\",\"code\":21},{\"result\":\"hello\",\"date\":\"2018-06-04 15:26:32\",\"code\":22},{\"result\":\"hello\",\"date\":\"2018-06-04 15:26:32\",\"code\":23}]"
        val jsonBean =
            FrameManager.jsonManager().fromJsonList(string, JsonBean::class.java)
        text_result_common.text = jsonBean.toString()
        Logger.i("json to String--$jsonBean")
    }
}
