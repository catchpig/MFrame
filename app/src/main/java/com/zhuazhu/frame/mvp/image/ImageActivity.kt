package com.zhuazhu.frame.mvp.image

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.zhuazhu.frame.R
import kotlinx.android.synthetic.main.activity_image.recycler_image

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        recycler_image.apply {
            layoutManager = LinearLayoutManager(this@ImageActivity)
            setHasFixedSize(true)
            adapter = ImageAdapter()
        }
    }
}
