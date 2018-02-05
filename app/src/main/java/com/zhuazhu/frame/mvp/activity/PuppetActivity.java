package com.zhuazhu.frame.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.zhuazhu.frame.R;
import mejust.frame.annotation.LayoutId;
import mejust.frame.image.ImageUtils;
import mejust.frame.mvp.view.BaseActivity;

@LayoutId(R.layout.activity_puppet)
public class PuppetActivity extends BaseActivity {
    private String url = "http://image.tianjimedia.com/uploadImages/2015/285/24/586K2UOWHG9D.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportFragmentManager().beginTransaction()
        //        .add(R.id.fragment, new PuppetFragment())
        //        .commitAllowingStateLoss();
        ImageView imageView = findViewById(R.id.image);
        ImageUtils.show(imageView, url);
    }
}
