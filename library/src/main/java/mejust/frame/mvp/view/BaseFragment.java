package mejust.frame.mvp.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mejust.frame.annotation.utils.StatusBarUtils;
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

    private Unbinder mUnbinder;
    private BaseActivity mActivity;
    private ImmersionBar mImmersionBar;

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
        View view = AnnotationBind.injectLayoutId(this, inflater, container);
        initStatusBar();
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
        if(mImmersionBar ==null){
            mImmersionBar = ImmersionBar.with(this);
        }
        if (StatusBarUtils.isStatusBar(this)) {
            AnnotationBind.injectStatusBar(this, mImmersionBar);
        } else {
            AnnotationBind.configStatusBar(mActivity.getStatusBarOption(), mImmersionBar);
        }
    }

    @Override
    public void loadingView() {
        mActivity.loadingView();
    }

    @Override
    public void loadingDialog() {
        mActivity.loadingDialog();
    }

    @Override
    public void show(String msg) {
        mActivity.show(msg);
    }

    @Override
    public void showToastDialog(CharSequence msg, View.OnClickListener clickListener) {
        mActivity.showToastDialog(msg, clickListener);
    }

    @Override
    public void hidden() {
        mActivity.hidden();
    }

    @Override
    public void startLoginActivity() {
        mActivity.startLoginActivity();
    }

    @Override
    public FragmentActivity getViewActivity() {
        return getActivity();
    }

    /**
     * 获取状态栏高度(px)
     * @return
     */
    @Override
    public int getStatusBarHeight() {
        return ImmersionBar.getStatusBarHeight(getActivity());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden && mImmersionBar != null){
            mImmersionBar.init();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
        if(mImmersionBar!=null){
            mImmersionBar.destroy();
        }
    }
}
