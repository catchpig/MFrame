package mejust.frame.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import conm.zhuazhu.common.utils.KeyboardUtils;
import mejust.frame.R;
import mejust.frame.annotation.StatusBarConfig;
import mejust.frame.app.AppConfig;
import mejust.frame.mvp.BaseContract;
import mejust.frame.utils.ContentViewBind;
import mejust.frame.utils.StatusBarUtil;
import mejust.frame.utils.TitleBarUtil;
import mejust.frame.widget.NetWorkControlView;
import mejust.frame.widget.ToastFrame;
import mejust.frame.widget.dialog.FrameDialogAction;
import mejust.frame.widget.title.StatusBar;
import mejust.frame.widget.title.TitleBar;

/**
 * <p>
 * 添加布局文件,不再调用setContentView方法,在继承的子类上添加<br/>
 * {@link mejust.frame.annotation.LayoutId}注解<br/><br/>
 * <p>
 * 状态栏设置,用注解<br/>
 * {@link StatusBarConfig}
 * <p>
 * 在manifest中必须开启权限:<br/>
 * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}
 * <p>
 *
 * @author litao wangpeifeng
 * @date 2018/04/18 11:10
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {

    private FrameLayout mLayoutRoot;
    private Unbinder mUnBinder;
    private NetWorkControlView netWorkControlView;
    private StatusBar mStatusBar;
    private ActivityStatusViewControl statusViewControl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 方法调用顺序不能改变
        super.setContentView(R.layout.view_root);
        ContentViewBind.injectLayoutId(this);
        mUnBinder = ButterKnife.bind(this);
        initBar();
        netWorkControlView = findViewById(R.id.network_control_view);
        statusViewControl = new ActivityStatusViewControl(this, mLayoutRoot);
    }

    @Override
    public void setContentView(View view) {
        if (mLayoutRoot == null) {
            mLayoutRoot = findViewById(R.id.layout_body);
        }
        mLayoutRoot.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    protected void onStart() {
        super.onStart();
        netWorkControlView.registerNetChangeListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        netWorkControlView.unRegisterNetChangeListener();
    }

    @Override
    protected void onDestroy() {
        statusViewControl.destroyControl();
        super.onDestroy();
        mUnBinder.unbind();
        if (mStatusBar != null) {
            mStatusBar.destroy();
        }
    }

    @Override
    public void finish() {
        KeyboardUtils.hideSoftInput(this);
        super.finish();
    }

    /**
     * 初始化TitleBar
     */
    private void initBar() {
        TitleBar mTitleBar = findViewById(R.id.title_bar);
        TitleBarUtil.inject(this, mTitleBar, AppConfig.getTitleBarSetting());
        mStatusBar = StatusBarUtil.init(this, mTitleBar);
        configBar(mTitleBar, mStatusBar);
    }

    @Override
    public void showToast(String msg) {
        ToastFrame.show(msg);
    }

    @Override
    public void showMessageDialog(String title, CharSequence msg,
            @NonNull FrameDialogAction.ActionListener actionListener) {
        statusViewControl.showDetermineMessageDialog(title, msg, actionListener);
    }

    @Override
    public void showLoading(boolean isLoadingDialog) {
        statusViewControl.showLoading(isLoadingDialog);
    }

    @Override
    public void hideLoading() {
        statusViewControl.hideLoading();
    }

    @Override
    public void startLoginActivity() {
        AppConfig.startLoginActivity(this);
    }

    @Override
    public FragmentActivity getViewActivity() {
        return this;
    }

    @Override
    public void finishView() {
        finish();
    }

    /**
     * TitleBar 和 StatusBar配置
     */
    protected void configBar(TitleBar titleBar, StatusBar statusBar) {

    }
}
