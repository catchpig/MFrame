package mejust.frame.utils;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;
import mejust.frame.R;
import mejust.frame.annotation.StatusBarConfig;
import mejust.frame.mvp.view.BaseActivity;
import mejust.frame.mvp.view.BaseFragment;
import mejust.frame.widget.title.StatusBar;
import mejust.frame.widget.title.TitleBar;

/**
 * 创建时间:2017/12/25 12:50<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: <br/>
 * 描述:
 */
public class StatusBarUtil {

    /**
     * 初始化状态栏
     */
    public static StatusBar init(@NonNull BaseActivity activity, @NonNull TitleBar mTitleBar) {
        StatusBar statusBar = null;
        StatusBarConfig statusBarConfig = activity.getClass().getAnnotation(StatusBarConfig.class);
        if (statusBarConfig == null || statusBarConfig.isInitActivity()) {
            statusBar = StatusBar.with(activity);
            if (mTitleBar.getVisibility() == View.VISIBLE) {
                int color = mTitleBar.getTitleBarSetting().getBackgroundColor();
                statusBar = statusBar.statusBarView(R.id.top_view).statusBarColorInt(color);
                boolean darkStatus = statusBarConfig != null && (statusBarConfig.isDarkStatus());
                if (darkStatus || isDarkStatus(color)) {
                    statusBar = statusBar.statusBarDarkFont(true, 0.2f);
                }
            }
            statusBar.init();
        }
        return statusBar;
    }

    /**
     * 初始化状态栏
     */
    public static StatusBar init(@NonNull BaseFragment baseFragment,
            @NonNull HideStatusListener hideStatusListener) {
        StatusBarConfig statusBarConfig =
                AnnotationUtils.annotation(baseFragment.getClass(), StatusBarConfig.class);
        StatusBar statusBar = null;
        if (statusBarConfig != null && statusBarConfig.isInitFragment()) {
            hideStatusListener.change(statusBarConfig.isHideStatus());
            statusBar = StatusBar.with(baseFragment.getActivity(), baseFragment);
            if (statusBarConfig.isDarkStatus()) {
                statusBar = statusBar.statusBarDarkFont(true, 0.2f);
            }
            statusBar.init();
        }
        return statusBar;
    }

    /**
     * 判断是否需要自动设置灰色状态栏
     * <p>
     * 在#F0F0F0 和 #FFFFFF之间
     *
     * @param colorInt 状态栏颜色
     */
    private static boolean isDarkStatus(@ColorInt int colorInt) {
        int blue = Color.blue(colorInt);
        int red = Color.blue(colorInt);
        int green = Color.green(colorInt);
        return blue == red && red == green && blue <= 255 && blue >= 240;
    }

    public interface HideStatusListener {
        /**
         * fragment is hide
         *
         * @param isHide true
         */
        void change(boolean isHide);
    }
}
