package com.zhuazhu.frame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.zhuazhu.annotation.LayoutId;
import com.zhuazhu.frame.data.HttpHelper;
import com.zhuazhu.frame.di.component.DaggerMainComponent;
import com.zhuazhu.frame.di.module.MainModule;
import javax.inject.Inject;
import mejust.frame.mvp.view.BaseActivity;
import mejust.frame.widget.TitleBarOptions;

@LayoutId(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder()
                .appComponent(FrameApplication.getAppComponent())
                .mainModule(new MainModule())
                .build()
                .inject(this);
        TitleBarOptions options = new TitleBarOptions();
        options.setTitleString("Hello World");
        options.setTitleStringColor(getResources().getColor(R.color.colorPrimary));
        options.setTitleStringSize(18);
        options.setImgLeftMainId(R.mipmap.cash_ing);
        options.setTextLeft("返回");
        options.setTextLeftColor(getResources().getColor(R.color.colorPrimary));
        options.setTextLeftSize(14);
        options.setImgRightMainId(R.mipmap.cash_ing);
        options.setTextRight("退出登录");
        options.setTextRightColor(getResources().getColor(R.color.colorPrimary));
        options.setTextRightSize(14);
        setTitleBar(options);
    }

    @Inject
    HttpHelper httpHelper;

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.image:
                intent.setClass(this, ImageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
