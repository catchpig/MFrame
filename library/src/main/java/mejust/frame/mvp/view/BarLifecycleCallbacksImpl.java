package mejust.frame.mvp.view;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import mejust.frame.R;
import mejust.frame.data.annotation.Title;
import mejust.frame.data.annotation.TitleMainTextStyle;
import mejust.frame.data.annotation.TitleRightMenu;
import mejust.frame.data.annotation.TitleRightMenuImage;
import mejust.frame.data.annotation.TitleRightMenuText;
import mejust.frame.widget.NetWorkControlView;
import mejust.frame.widget.title.TitleBar;

/**
 * 处理标题栏和状态栏
 *
 * @author zhuazhu
 **/
public class BarLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity instanceof BaseActivity) {
            Title title = activity.getClass().getAnnotation(Title.class);
            BaseActivity baseActivity = (BaseActivity) activity;
            baseActivity.titleBar = activity.findViewById(R.id.title_bar_base);
            if (title == null) {
                baseActivity.titleBar.setVisibility(View.GONE);
                return;
            }
            if (title.backgroundColorRes() != -1) {
                baseActivity.titleBar.setTitleBarBackgroundColor(
                        ContextCompat.getColor(activity, title.backgroundColorRes()));
            }
            if (title.titlePaddingSize() != -1) {
                baseActivity.titleBar.setTitleBarPaddingSize(title.titlePaddingSize());
            }
            if (title.showBackMenu()) {
                baseActivity.titleBar.setBackMenu(v -> activity.finish());
            } else {
                baseActivity.titleBar.setBackMenuVisible(false);
            }
            setTitleMainText(title, baseActivity.titleBar);
            setTitleRightMenu(title, baseActivity.titleBar);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activity instanceof BaseActivity) {
            NetWorkControlView netWorkControlView =
                    activity.findViewById(R.id.network_control_view);
            netWorkControlView.registerNetChangeListener();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity instanceof BaseActivity) {
            NetWorkControlView netWorkControlView =
                    activity.findViewById(R.id.network_control_view);
            netWorkControlView.unRegisterNetChangeListener();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    /**
     * 设置右边菜单
     */
    private void setTitleRightMenu(Title title, TitleBar titleBar) {
        TitleRightMenu[] titleRightMenu = title.titleRightMenu();
        for (TitleRightMenu rightMenu : titleRightMenu) {
            TitleRightMenuImage menuImage = rightMenu.menuImage();
            TitleRightMenuText menuText = rightMenu.menuText();
            if (menuImage.value() != -1) {
                titleBar.addRightImageMenu(rightMenu.viewId(), menuImage.value(),
                        rightMenu.marginLeft(), rightMenu.marginRight());
            } else {
                titleBar.addRightTextMenu(rightMenu.viewId(), menuText.value(), menuText.textSize(),
                        menuText.textColor(), rightMenu.marginLeft(), rightMenu.marginRight());
            }
        }
    }

    /**
     * 设置标题文字
     */
    private void setTitleMainText(Title title, TitleBar titleBar) {
        titleBar.setMainTitle(title.value());
        TitleMainTextStyle titleMainTextStyle = title.mainTextStyle();
        titleBar.setTitleBarMainTextStyle(titleMainTextStyle.textColor(),
                titleMainTextStyle.textSize(), titleMainTextStyle.textCenter());
    }
}
