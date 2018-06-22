package com.zhuazhu.frame.mvp.main.view

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.zhuazhu.frame.R.layout
import com.zhuazhu.frame.mvp.common.CommonActivity
import com.zhuazhu.frame.mvp.http.view.HttpActivity
import com.zhuazhu.frame.mvp.image.ImageActivity
import com.zhuazhu.frame.mvp.main.model.PageItem
import kotlinx.android.synthetic.main.activity_main.recycler_main
import mejust.frame.data.annotation.Title
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MainActivity : AppCompatActivity() {

    private val pageItems = listOf(
        PageItem("image", ImageActivity::class.java),
        PageItem("http", HttpActivity::class.java),
        PageItem("common", CommonActivity::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        recycler_main.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = MainAdapter(pageItems)
        }
        requestPermissionWithPermissionCheck()
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    fun requestPermission() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        onRequestPermissionsResult(requestCode, grantResults)
    }
}
