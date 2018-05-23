package mejust.frame.mvp.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import mejust.frame.widget.dialog.FrameDialog;
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

    private FrameLayout mLayoutBody;
    private Unbinder mUnBinder;
    private View mLoadingView;
    private Dialog messageDialog;
    private Dialog loadingDialog;
    protected StatusBar mStatusBar;

    protected TitleBar mTitleBar;

    private NetWorkControlView netWorkControlView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 方法调用顺序不能改变
        super.setContentView(R.layout.view_root);
        ContentViewBind.injectLayoutId(this);
        mUnBinder = ButterKnife.bind(this);
        initTitleBar();
        mStatusBar = StatusBarUtil.init(this, mTitleBar);
        initLoadingView();
        netWorkControlView = findViewById(R.id.network_control_view);
    }

    @Override
    public void setContentView(View view) {
        if (mLayoutBody == null) {
            mLayoutBody = findViewById(R.id.layout_body);
        }
        mLayoutBody.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
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
        if (messageDialog != null) {
            messageDialog.cancel();
            messageDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        if (mStatusBar != null) {
            mStatusBar.destroy();
        }
    }

    @Override
    public void finish() {
        super.finish();
        KeyboardUtils.hideSoftInput(this);
    }

    private void initLoadingView() {
        setContentView(R.layout.view_loading);
        mLoadingView = findViewById(R.id.layout_loading);
        mLoadingView.setVisibility(View.GONE);
    }

    /**
     * 初始化TitleBar
     */
    private void initTitleBar() {
        mTitleBar = findViewById(R.id.title_bar);
        TitleBarUtil.inject(this, mTitleBar, AppConfig.getTitleBarSetting());
    }

    @Override
    public void show(String msg) {
        ToastFrame.show(msg);
    }

    @Override
    public void showToastDialog(String title, CharSequence msg,
            @NonNull FrameDialogAction.ActionListener actionListener) {
        runOnUiThread(() -> {
            if (messageDialog != null) {
                messageDialog.cancel();
            }
            messageDialog = new FrameDialog.MessageDialogBuilder(this).setTitle(title)
                    .setMessage(msg)
                    .addAction(new FrameDialogAction(getString(R.string.determine_frame),
                            actionListener))
                    .create();
            messageDialog.show();
        });
    }

    @Override
    public void loadingDialog() {
        runOnUiThread(() -> {
            if (loadingDialog != null) {
                return;
            }
            loadingDialog = new FrameDialog.LoadingDialogBuilder(this).create();
            loadingDialog.show();
        });
    }

    @Override
    public void loadingView() {
        runOnUiThread(() -> {
            if (mLoadingView == null) {
                return;
            }
            mLoadingView.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hidden() {
        runOnUiThread(() -> {
            if (loadingDialog != null) {
                loadingDialog.cancel();
                loadingDialog = null;
            }
            if (mLoadingView != null) {
                mLoadingView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void startLoginActivity() {
        Class<? extends Activity> loginClass = AppConfig.getLoginClass();
        if (loginClass == null) {
            throw new IllegalArgumentException("login Activity class is null,please set");
        }
        startActivity(new Intent(this, loginClass));
    }

    @Override
    public FragmentActivity getViewActivity() {
        return this;
    }

    @Override
    public void finishView() {
        finish();
    }
}
