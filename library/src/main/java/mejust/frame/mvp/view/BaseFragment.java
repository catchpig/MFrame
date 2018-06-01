package mejust.frame.mvp.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mejust.frame.mvp.BaseContract;
import mejust.frame.utils.ContentViewBind;
import mejust.frame.utils.StatusBarUtil;
import mejust.frame.widget.dialog.FrameDialogAction;
import mejust.frame.widget.title.StatusBar;

/**
 * 创建时间:2017-12-21 18:52<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 18:52<br/>
 * 描述:
 */
public abstract class BaseFragment extends Fragment implements BaseContract.View {

    private Unbinder mUnBinder;
    private BaseActivity mActivity;
    protected StatusBar mStatusBar;
    private boolean isHideStatus;

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            mActivity = (BaseActivity) getActivity();
        }
    }

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return ContentViewBind.injectLayoutId(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        mStatusBar = StatusBarUtil.init(this, isHide -> isHideStatus = isHide);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (isHideStatus && !hidden && mStatusBar != null) {
            mStatusBar.init();
        }
    }

    @Override
    public void showLoading(boolean isLoadingDialog) {
        mActivity.showLoading(isLoadingDialog);
    }

    @Override
    public void showToast(String msg) {
        mActivity.showToast(msg);
    }

    @Override
    public void showMessageDialog(String title, CharSequence msg,
            @NonNull FrameDialogAction.ActionListener actionListener) {
        mActivity.showMessageDialog(title, msg, actionListener);
    }

    @Override
    public void hideLoading() {
        mActivity.hideLoading();
    }

    @Override
    public void finishActivity() {
        mActivity.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (mStatusBar != null) {
            mStatusBar.destroy();
        }
    }
}
