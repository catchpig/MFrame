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
import mejust.frame.annotation.utils.AnnotionUtils;
import mejust.frame.annotation.utils.AnnotationBind;
import mejust.frame.mvp.BaseContract;
import mejust.frame.widget.title.StatusBar;

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
    return AnnotationBind.injectLayoutId(this, inflater, container);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mUnbinder = ButterKnife.bind(this, view);
    initStatusBar();
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (isHideStatus && !hidden && mStatusBar != null) {
      mStatusBar.init();
    }
  }

  /**
   * 初始化状态栏
   */
  private void initStatusBar() {
    mejust.frame.annotation.StatusBar statusBar =
        AnnotionUtils.annotation(this.getClass(), mejust.frame.annotation.StatusBar.class);
    if (statusBar != null && statusBar.isInitFragment()) {
      isHideStatus = statusBar.isHideStatus();
      mStatusBar = StatusBar.with(getViewActivity(), this);
      if (statusBar.isDarkStatus()) {
        mStatusBar = mStatusBar.statusBarDarkFont(true, 0.2f);
      }
      mStatusBar.init();
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

  @Override
  public void finishView() {
    getViewActivity().finish();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (mUnbinder != null) {
      mUnbinder.unbind();
    }
    if (mStatusBar != null) {
      mStatusBar.destroy();
    }
  }
}
