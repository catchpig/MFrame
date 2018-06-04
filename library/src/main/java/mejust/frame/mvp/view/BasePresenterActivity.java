package mejust.frame.mvp.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
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

public abstract class BasePresenterActivity<P extends BaseContract.Presenter> extends BaseActivity {

    @Inject
    protected P mPresenter;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
        injectComponent();
        initView();
        mPresenter.onCreate();
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    /**
     * 初始化参数数据
     */
    protected abstract void initParam();

    /**
     * 依赖注入
     */
    protected abstract void injectComponent();

    /**
     * 初始化View
     */
    protected abstract void initView();
}
