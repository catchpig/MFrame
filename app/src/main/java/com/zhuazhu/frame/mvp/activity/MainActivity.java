package com.zhuazhu.frame.mvp.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.zhuazhu.annotation.LayoutId;
import com.zhuazhu.frame.R;
import com.zhuazhu.frame.di.module.MainModule;
import com.zhuazhu.frame.mvp.application.FrameApplication;
import com.zhuazhu.frame.mvp.contract.MainContract;
import com.zhuazhu.frame.mvp.presenter.MainPresenterImp;

import mejust.frame.annotation.TextRightFirstEvent;
import mejust.frame.annotation.TitileBar;
import mejust.frame.mvp.view.BasePresenterActivity;

@LayoutId(R.layout.activity_main)
@TitileBar("首页")
public class MainActivity extends BasePresenterActivity<MainPresenterImp>
        implements MainContract.View {
    @Override
    protected void initParam() {
    }

    @Override
    protected void injectComponent() {
        FrameApplication.getAppComponent()
                .mainComponent(new MainModule(this))
                .inject(this);
    }

    @Override
    protected void initView() {

    }
    @TextRightFirstEvent("详情")
    protected void detail(TextView v){
        loadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hidden();
            }
        },2000);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.image:
                intent.setClass(this, ImageActivity.class);
                startActivity(intent);
                break;
            case R.id.recycler:
                intent.setClass(this, RecyclerActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}