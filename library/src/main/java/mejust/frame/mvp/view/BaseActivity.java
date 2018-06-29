package mejust.frame.mvp.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.gyf.barlibrary.ImmersionBar;
import conm.zhuazhu.common.utils.KeyboardUtils;
import mejust.frame.R;
import mejust.frame.data.annotation.Title;
import mejust.frame.mvp.BaseContract;
import mejust.frame.mvp.view.support.ActivityStateViewControl;
import mejust.frame.widget.ToastFrame;
import mejust.frame.widget.dialog.FrameDialogAction;
import mejust.frame.widget.title.TitleBar;

/**
 * <p>
 * 标题文字设置,用注解
 * {@link Title}<br/>
 * <p>
 * 标题栏按钮的对应的id,通过{@link butterknife.OnClick}监听点击事件
 * {@link R.id.title_right_menu_first_text,R.id.title_right_menu_second_text,R.id.title_right_menu_first_drawable,R.id.title_right_menu_second_drawable}
 * <p><br/>
 * 在manifest中必须开启权限:<br/>
 * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}
 * <p>
 *
 * @author litao wangpeifeng
 * @date 2018/04/18 11:10
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {

    public static final int HANDLER_MSG_LOADING_DIALOG_OPEN = 0x110;
    public static final int HANDLER_MSG_LOADING_VIEW_OPEN = 0x111;
    public static final int HANDLER_MSG_LOADING_CLOSE = 0x112;

    private FrameLayout mLayoutRoot;
    private Unbinder mUnBinder;
    private ActivityStateViewControl statusViewControl;

    protected ImmersionBar mImmersionBar;
    protected TitleBar titleBar;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (statusViewControl == null) {
                return false;
            }
            switch (msg.what) {
                case HANDLER_MSG_LOADING_DIALOG_OPEN:
                    statusViewControl.showLoading(true);
                    break;
                case HANDLER_MSG_LOADING_VIEW_OPEN:
                    statusViewControl.showLoading(false);
                    break;
                case HANDLER_MSG_LOADING_CLOSE:
                    statusViewControl.hideLoading();
                    break;
                default:
                    return handleActivityMessage(msg);
            }
            return true;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 方法调用顺序不能改变
        super.setContentView(R.layout.view_root);
        setContentView(getLayoutId(savedInstanceState));
        mUnBinder = ButterKnife.bind(this);
        statusViewControl = new ActivityStateViewControl(this, mLayoutRoot);
        super.onCreate(savedInstanceState);
    }

    /**
     * 获取布局文件
     *
     * @param savedInstanceState Bundle value
     * @return content view layout id
     */
    @LayoutRes
    protected abstract int getLayoutId(@Nullable Bundle savedInstanceState);

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
    protected void onDestroy() {
        statusViewControl.destroyControl();
        statusViewControl = null;
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public void finish() {
        KeyboardUtils.hideSoftInput(this);
        super.finish();
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
        int what = isLoadingDialog ? BaseActivity.HANDLER_MSG_LOADING_DIALOG_OPEN
                : BaseActivity.HANDLER_MSG_LOADING_VIEW_OPEN;
        handler.sendEmptyMessage(what);
    }

    @Override
    public void hideLoading() {
        handler.sendEmptyMessage(BaseActivity.HANDLER_MSG_LOADING_CLOSE);
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    @Override
    public void finishActivity() {
        finish();
    }

    /**
     * 设置状态栏颜色
     */
    protected void setStateBarColor(@ColorRes int color) {
        if (mImmersionBar == null) {
            mImmersionBar = ImmersionBar.with(this).statusBarView(R.id.top_view);
        }
        mImmersionBar.statusBarColor(color).init();
    }

    /**
     * 处理Handler信息
     */
    protected boolean handleActivityMessage(Message message) {
        return false;
    }
}
