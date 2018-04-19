package com.zhuazhu.frame.mvp.activity;

import android.view.View;
import android.widget.Toast;
import com.zhuazhu.frame.R;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.TitleBarConfig;
import mejust.frame.annotation.TitleBarMenu;
import mejust.frame.annotation.TitleBarMenuLocation;
import mejust.frame.mvp.view.BaseActivity;

/**
 * @author wangpeifeng
 * @date 2018/04/18 17:12
 */
@LayoutId(R.layout.activity_puppet)
@TitleBarConfig(textValue = "你好")
public class PuppetActivity extends BaseActivity {

    private String url = "http://image.tianjimedia.com/uploadImages/2015/285/24/586K2UOWHG9D.jpg";

    @TitleBarMenu(location = TitleBarMenuLocation.leftSecondMenu, text = "tuichu")
    public void clickToast(View view) {
        Toast.makeText(this, "TuiChu", Toast.LENGTH_SHORT).show();
    }
}
