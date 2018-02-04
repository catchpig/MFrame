package mejust.frame.mvp.view;

import android.app.Dialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import conm.zhuazhu.common.utils.NetworkUtils;
import mejust.frame.R;
import mejust.frame.annotation.options.StatusBarOption;
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
        implements BaseContract.View, View.OnClickListener {

    private static DefaultActivityOption sActivityOption;
    private RelativeLayout mLayoutBody;
    private Unbinder mUnBinder;
    private TitleBar mTitleBar;
    private View mLoadingView;
    private Dialog mLoadingDialog;
    private NetworkReceiver mNetworkReceiver;
    private ToastDialog mToastDialog;
    private ImmersionBar mImmersionBar;

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
        //设置打开网络点击监听事件
        mNetworkTip.setOnClickListener(v -> {
            NetworkUtils.openWifiSettings();
        });
    }

    /**
     * 设置基类activity的参数(标题栏参数,状态栏颜色,登录页面)
     */
    public static void setDefaultActivityOption(DefaultActivityOption option) {
        sActivityOption = option;
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
            TitleBarOptions titleBarOptions = sActivityOption.titleBarOption();
            mTitleBar.setOptions(titleBarOptions);
            AnnotationBind.injectTitleBar(this, mTitleBar);
            //设置返回按钮监听事件(关闭当前页面)
            mTitleBar.setBackListener(v -> {
                finish();
            });
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
            mNetworkReceiver.setOnNetworkListener(network -> {
                if (network) {
                    //网络提示页面隐藏
                    mNetworkTip.setVisibility(View.GONE);
                } else {
                    //网络提示页面展示
                    mNetworkTip.setVisibility(View.VISIBLE);
                }
            });
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(mNetworkReceiver, filter);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mToastDialog != null) {
            mToastDialog.cancel();
            mToastDialog = null;
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
        if(mImmersionBar !=null){
            mImmersionBar.destroy();
        }
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
            AnnotationBind.configStatusBar(sActivityOption.statusBarOption(), mImmersionBar);
        }
    }

    /**
     * 获取状态栏公共参数
     * @return
     */
    public StatusBarOption getStatusBarOption(){
        return sActivityOption.statusBarOption();
    }
    /**
     * 获取状态栏高度(px)
     * @return
     */
    @Override
    public int getStatusBarHeight(){
        return ImmersionBar.getStatusBarHeight(this);
    }


    /**
     * 设置标题栏
     *
     * @param options 标题栏属性
     */
    protected void setTitleBar(@NonNull TitleBarOptions options) {
        mTitleBar.setOptions(options);
    }


    @Override
    public void show(String msg) {
        ToastMsg.makeText(msg);
    }

    @Override
    public void showToastDialog(CharSequence msg, View.OnClickListener clickListener) {
        runOnUiThread(() -> {
            if (mToastDialog != null) {
                mToastDialog.cancel();
            }
            mToastDialog = new ToastDialog.Builder(this).content(msg)
                    .setDetermine(true, clickListener)
                    .build();
            mToastDialog.show();
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

    @Override
    public void onClick(View v) {

    }
}
