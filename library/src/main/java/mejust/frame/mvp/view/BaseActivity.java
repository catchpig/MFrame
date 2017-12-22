package mejust.frame.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhuazhu.bind.AnnotationBind;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mejust.frame.R;
import mejust.frame.mvp.BaseContract;
import mejust.frame.widget.title.TitleBar;
import mejust.frame.widget.title.TitleBarOptions;

/**
 * 创建时间:2017-12-21 10:41<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 10:41<br/>
 * 描述: 无MVP的基类，添加了TitleBar管理<br/>
 * 添加布局文件,不再调用setContentView方法,在继承的子类上添加@layoutId注解
 */
public class BaseActivity extends AppCompatActivity implements BaseContract.View {

    private LinearLayout layoutRoot;
    private Unbinder unbinder;
    private TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.mframe_view_root);
        AnnotationBind.inject(this);
        unbinder = ButterKnife.bind(this);
        titleBar = layoutRoot.findViewById(R.id.title_bar);
    }

    @Override
    public void setContentView(View view) {
        layoutRoot = findViewById(R.id.layout_root);
        if (layoutRoot == null) {
            return;
        }
        ViewGroup.LayoutParams params =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        layoutRoot.addView(view, params);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 设置标题栏
     *
     * @param options 标题栏属性
     */
    public void setTitleBar(@NonNull TitleBarOptions options) {
        titleBar.setVisibility(View.VISIBLE);
        titleBar.setOptions(options);
    }

    @Override
    public void show(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadingDialog() {
        //TODO 展示loading的弹窗
    }

    @Override
    public void loadingView() {
        //TODO 展示loading的视图
    }

    @Override
    public void hidden() {
        //TODO 隐藏loading
    }

    @Override
    public void startLoginActivity() {
        //TODO 跳转登录界面
    }

    @Override
    public Context getContext() {
        return this;
    }
}
