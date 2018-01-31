package mejust.frame.mvp.view;

import android.app.Dialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import conm.zhuazhu.common.utils.NetworkUtils;
import mejust.frame.R;
import mejust.frame.annotation.utils.StatusBarUtils;
import mejust.frame.annotation.utils.TitleBarAnnotationUtils;
import mejust.frame.bind.AnnotationBind;
import mejust.frame.dialog.ToastDialog;
import mejust.frame.mvp.BaseContract;
import mejust.frame.mvp.view.option.DefaultActivityOption;
import mejust.frame.receiver.NetworkReceiver;
import mejust.frame.receiver.NetworkReceiver.OnNetworkListener;
import mejust.frame.widget.ToastMsg;
import mejust.frame.widget.title.TitleBar;
import mejust.frame.widget.title.TitleBarOptions;
import qiu.niorgai.StatusBarCompat;

/**
 * 创建时间:2017-12-21 10:41<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017-12-21 10:41<br/>
 * 描述: 无MVP的基类<br/>
 * <p>
 * 添加布局文件,不再调用setContentView方法,在继承的子类上添加<br/>
 * {@link mejust.frame.annotation.LayoutId}注解<br/><br/>
 * <p>
 * 默认的标题,用标题栏注解<br/>
 * {@link mejust.frame.annotation.TitleBar}<br/><br/>
 * <p>
 * 标题栏右边第一个文字的按钮监听和文字的设置用注解<br/>
 * {@link mejust.frame.annotation.TextRightFirstEvent}<br/><br/>
 * <p>
 * 标题栏右边第二个文字的按钮监听和文字的设置用注解<br/>
 * {@link mejust.frame.annotation.TextRightSecondEvent}<br/><br/>
 * <p>
 * 标题栏右边第一个图片的按钮监听和文字的设置用注解<br/>
 * {@link mejust.frame.annotation.ImageRightFirstEvent}<br/><br/>
 * <p>
 * 标题栏右边第二个图片的按钮监听和文字的设置用注解<br/>
 * {@link mejust.frame.annotation.ImageRightSecondEvent}<br/><br/>
 * <p>
 * 状态栏设置,用注解<br/>
 * {@link mejust.frame.annotation.StatusBar}
 * <p>
 * 在manifest中必须开启权限:<br/>
 * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}
 * <p>
 */
public abstract class BaseActivity extends AppCompatActivity
        implements BaseContract.View, View.OnClickListener, OnNetworkListener {

    private static DefaultActivityOption sActivityOption;
    private TitleBarOptions titleBarOptions;
    private RelativeLayout mLayoutBody;
    private Unbinder mUnBinder;
    private TitleBar mTitleBar;
    private View mLoadingView;
    private Dialog mLoadingDialog;
    private NetworkReceiver mNetworkReceiver;
    private ToastDialog toastDialog;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * 方法调用顺序不能改变
         */
        super.setContentView(R.layout.view_root);
        initStatusBar();
        AnnotationBind.injectLayoutId(this);
        mUnBinder = ButterKnife.bind(this);
        initTitleBar();
        initLoadingView();
        initNetworkTip();
    }

    private LinearLayout mNetworkTip;

    /**
     * 初始化网络未打开的提示控件
     */
    private void initNetworkTip() {
        mNetworkTip = findViewById(R.id.network_tip);
        mNetworkTip.setOnClickListener(this);
    }

    /**
     * 设置基类activity的参数(标题栏参数,状态栏颜色,登录页面)
     */
    public static void setDefaultActivityOption(DefaultActivityOption option) {
        sActivityOption = option;
    }

    /**
     * 获取当前页面的TitleBar配置
     */
    public TitleBarOptions getTitleBarOptions() {
        if (titleBarOptions == null) {
            throw new IllegalStateException("设置TitleBar,必须添加@TitleBar注解");
        }
        return titleBarOptions;
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
        //有@TitleBar这个注解,才执行下面的操作
        if (TitleBarAnnotationUtils.isTitleBarAnnotation(getClass())) {
            titleBarOptions = sActivityOption.titleBarOption();
            setTitleBar(titleBarOptions);
            AnnotationBind.injectTitleBar(this, mTitleBar);
            mTitleBar.setBackListener(this);
        } else {
            mTitleBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setContentView(View view) {
        if (mLayoutBody == null) {
            mLayoutBody = findViewById(R.id.layout_body);
        }
        ViewGroup.LayoutParams params =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        mLayoutBody.addView(view, params);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册网络变化广播监听
        if (mNetworkReceiver == null) {
            mNetworkReceiver = new NetworkReceiver();
            mNetworkReceiver.setOnNetworkListener(this);
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(mNetworkReceiver, filter);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (toastDialog != null) {
            toastDialog.cancel();
            toastDialog = null;
        }
        if (mNetworkReceiver != null) {
            unregisterReceiver(mNetworkReceiver);
            mNetworkReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
        if (StatusBarUtils.isStatusBar(this)) {
            AnnotationBind.injectStatusBar(this);
        } else {
            setStatusBarColor(sActivityOption.statusBarColor());
        }
    }

    /**
     * 状态栏透明
     */
    public void translucentStatusBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

    /**
     * 设置状态栏颜色
     */
    public void setStatusBarColor(@ColorRes int color) {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, color));
    }

    /**
     * 设置标题栏
     *
     * @param options 标题栏属性
     */
    public void setTitleBar(@NonNull TitleBarOptions options) {
        mTitleBar.setOptions(options);
    }

    /**
     * 设置全屏
     */
    public void fullScreen() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void show(String msg) {
        ToastMsg.makeText(msg);
    }

    @Override
    public void showToastDialog(CharSequence msg, View.OnClickListener clickListener) {
        runOnUiThread(() -> {
            if (toastDialog != null) {
                toastDialog.cancel();
            }
            toastDialog = new ToastDialog.Builder(this).content(msg)
                    .setDetermine(true, clickListener)
                    .build();
            toastDialog.show();
        });
    }

    @Override
    public void loadingDialog() {
        runOnUiThread(() -> {
            if (mLoadingDialog != null) {
                return;
            }
            mLoadingDialog = new Dialog(BaseActivity.this, R.style.mframe_imagedialog);
            mLoadingDialog.setCancelable(false);// 不可以用“返回键”取消
            mLoadingDialog.setContentView(R.layout.dialog_loading);
            mLoadingDialog.show();
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
            if (mLoadingDialog != null) {
                mLoadingDialog.cancel();
                mLoadingDialog = null;
            }
            if (mLoadingView != null) {
                mLoadingView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void startLoginActivity() {
        sActivityOption.login(this);
    }

    @Override
    public FragmentActivity getViewActivity() {
        return this;
    }

    @CallSuper
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mframe_back_layout) {//返回
            finish();
        } else if (i == R.id.network_tip) {
            NetworkUtils.openWifiSettings();
        }
    }

    @Override
    public void onNetwork(boolean network) {
        if (network) {
            mNetworkTip.setVisibility(View.GONE);
        } else {
            mNetworkTip.setVisibility(View.VISIBLE);
        }
    }
}
