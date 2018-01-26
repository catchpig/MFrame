package com.zhuazhu.frame.mvp.activity;

import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.zhuazhu.frame.R;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.TitleBar;
import mejust.frame.mvp.view.BaseActivity;

@LayoutId(R.layout.activity_puppet)
@TitleBar("Fragment")
public class PuppetActivity extends BaseActivity {

    @BindView(R.id.bt_puppet)
    Button btPuppet;

    @OnClick(R.id.bt_puppet)
    public void clickButton(View view) {
        startLoginActivity();
    }
}
