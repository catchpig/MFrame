package com.zhuazhu.frame.mvp.http.view

import android.os.Bundle
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
import mejust.frame.data.annotation.TitleMainTextStyle
import mejust.frame.data.annotation.TitleRightMenu
import mejust.frame.data.annotation.TitleRightMenuImage
import mejust.frame.data.annotation.TitleRightMenuText
import mejust.frame.mvp.view.BasePresenterActivity

@Title(
    "请求",
    backgroundColorRes = R.color.color_000,
    titlePaddingSize = 0,
    showBackMenu = false,
    mainTextStyle = TitleMainTextStyle(
        textColor = R.color.colorPrimary,
        textSize = 21,
        textCenter = false
    ),
    titleRightMenu = [(TitleRightMenu(
        viewId = R.id.title_right_menu_first_text,
        menuText = TitleRightMenuText("菜单")
    )), (TitleRightMenu(
        viewId = R.id.title_right_menu_first_drawable,
        menuImage = TitleRightMenuImage(R.drawable.ic_arrow_back_white)
    ))]
)
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
        titleBar.bindRightMenuClickListener(R.id.title_right_menu_first_text) {
            Logger.i("点击了文字")
        }
        titleBar.bindRightMenuClickListener(R.id.title_right_menu_first_drawable) {
            Logger.i("点击了图标")
        }
    }

    override fun showResult(ss: String) {
        text_http_result.text = ss
    }
}
