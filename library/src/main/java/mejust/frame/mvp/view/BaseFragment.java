package mejust.frame.mvp.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mejust.frame.bind.AnnotationBind;
import mejust.frame.mvp.BaseContract;

/**
 * 创建时间:2017-12-21 18:52<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 18:52<br/>
 * 描述:
 */

public abstract class BaseFragment extends Fragment implements BaseContract.View {

    private Unbinder unbinder;
    private BaseActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            activity = (BaseActivity) getActivity();
        }
    }

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = AnnotationBind.injectLayoutId(this, inflater, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void loadingView() {
        activity.loadingView();
    }

    @Override
    public void loadingDialog() {
        activity.loadingDialog();
    }

    @Override
    public void show(String msg) {
        activity.show(msg);
    }

    @Override
    public void showToastDialog(String msg) {
        activity.showToastDialog(msg);
    }

    @Override
    public void hidden() {
        activity.hidden();
    }

    @Override
    public void startLoginActivity() {
        activity.startLoginActivity();
    }

    @Override
    public FragmentActivity getViewActivity() {
        return getActivity();
    }
}
