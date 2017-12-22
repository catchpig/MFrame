package mejust.frame.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import javax.inject.Inject;
import mejust.frame.mvp.BaseContract;

/**
 * 创建时间:2017-12-21 16:19<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 16:19<br/>
 * 描述:
 */

public abstract class BasePresenterActivity<P extends BaseContract.Presenter> extends BaseActivity
        implements BaseContract.View {

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent();
        mPresenter.onCreate();
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    /**
     * 初始化Data
     */
    protected abstract void initData();

    /**
     * 依赖注入
     */
    protected abstract void injectComponent();

    /**
     * 初始化View
     */
    protected abstract void initView();

    @Override
    public void show(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadingDialog() {
        //TODO 展示loading的弹窗
    }

    @Override
    public void loadingView() {
        //TODO 展示loading的视图
    }

    @Override
    public void hidden() {
        //TODO 隐藏loading
    }

    @Override
    public void startLoginActivity() {
        //TODO 跳转登录界面
    }

    @Override
    public Context getContext() {
        return this;
    }
}
