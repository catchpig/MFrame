package mejust.frame.mvp.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mejust.frame.R;
import mejust.frame.app.BaseApplication;
import mejust.frame.bind.AnnotationBind;
import mejust.frame.mvp.BaseContract;
import mejust.frame.mvp.view.option.DefaultActivityOption;
import mejust.frame.widget.title.DefaultTitleBarOption;
import mejust.frame.widget.title.TitleBar;
import mejust.frame.widget.title.TitleBarAnnotationUtils;
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
 * {@link mejust.frame.annotation.TitileBar}<br/><br/>
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
 */
public class BaseActivity extends AppCompatActivity
        implements BaseContract.View, View.OnClickListener {
    private static DefaultTitleBarOption sTitleBarOption;
    private static DefaultActivityOption sActivityOption;
    private RelativeLayout mLayoutBody;
    private Unbinder mUnBinder;
    private TitleBar mTitleBar;
    private View mLoadingView;
    private Dialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.view_root);
        initStatusBar();
        AnnotationBind.inject(this);
        mUnBinder = ButterKnife.bind(this);
        initTitleBar();
        initLoadingView();
        //添加activity到栈中
        BaseApplication.getInstance().addActivity(this);
    }

    private void initLoadingView() {
        setContentView(R.layout.view_loading);
        mLoadingView = findViewById(R.id.layout_loading);
        mLoadingView.setVisibility(View.GONE);
    }

    /**
     * 设置默认的标题栏参数
     */
    public static void setDefaultTitleBarOption(@NonNull DefaultTitleBarOption option) {
        sTitleBarOption = option;
    }

    /**
     * 初始化TitleBar
     */
    private void initTitleBar() {
        mTitleBar = findViewById(R.id.title_bar);
        //有@TitleBar这个注解,才执行下面的操作
        if (TitleBarAnnotationUtils.isTitleBarAnnotation(getClass())) {
            TitleBarOptions options = sTitleBarOption.createOption(mTitleBar);
            setTitleBar(options);
            TitleBarAnnotationUtils.inject(this, mTitleBar);
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
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        //结束Activity,从栈中移除该Activity
        BaseApplication.getInstance().finishActivity(this);
    }

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
        setStatusBarColor(sActivityOption.statusBarColor());
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

    @Override
    public void show(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadingDialog() {
        if (mLoadingDialog != null) {
            return;
        }
        mLoadingDialog = new Dialog(this, R.style.mframe_imagedialog);
        mLoadingDialog.setCancelable(false);// 不可以用“返回键”取消
        mLoadingDialog.setContentView(R.layout.dialog_loading);
        mLoadingDialog.show();
    }

    @Override
    public void loadingView() {
        if (mLoadingView == null) {
            return;
        }
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidden() {
        if (mLoadingDialog != null) {
            mLoadingDialog.cancel();
            mLoadingDialog = null;
        }
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        }
    }

    /**
     * 设置activity的默认参数
     */
    public static void setDefaultActivityOption(@NonNull DefaultActivityOption option) {
        sActivityOption = option;
    }

    @Override
    public void startLoginActivity() {
        sActivityOption.login(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @CallSuper
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mframe_back_layout) {//返回
            finish();
        }
    }
}
