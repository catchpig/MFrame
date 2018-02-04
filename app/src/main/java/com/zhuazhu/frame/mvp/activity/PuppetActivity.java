package com.zhuazhu.frame.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.zhuazhu.frame.R;
import com.zhuazhu.frame.fragment.PuppetFragment;

import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.TitleBar;
import mejust.frame.mvp.view.BaseActivity;

@LayoutId(R.layout.activity_puppet)
@TitleBar("Fragment")
public class PuppetActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment,new PuppetFragment()).commitAllowingStateLoss();
    }
}
