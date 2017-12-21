package mejust.frame.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import javax.inject.Inject;
import mejust.frame.mvp.BaseContract;

/**
 * 创建时间:2017-12-21 16:19<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 16:19<br/>
 * 描述:
 */

public abstract class BasePresenterActivity<T extends BaseContract.Presenter> extends BaseActivity
        implements BaseContract.View {

    @Inject
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        injectComponent();
        initView();
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
