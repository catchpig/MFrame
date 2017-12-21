package mejust.frame.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import javax.inject.Inject;
import mejust.frame.mvp.BaseContract;

/**
 * 创建时间:2017-12-21 21:23<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 21:23<br/>
 * 描述:
 */

public abstract class BasePresenterFragment<P extends BaseContract.Presenter> extends BaseFragment
        implements BaseContract.View {

    @Inject
    protected P presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        injectComponent();
        initView();
        presenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    protected abstract void initData();

    protected abstract void injectComponent();

    protected abstract void initView();

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
