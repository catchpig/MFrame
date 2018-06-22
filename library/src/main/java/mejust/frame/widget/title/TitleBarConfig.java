package mejust.frame.widget.title;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

/**
 * @author zhuazhu
 **/
public class TitleBarConfig {
    /**
     * title背景颜色
     */
    private int backgroundColor;
    /**
     * 标题大小
     */
    private float titleTextSize;
    /**
     * 标题颜色
     */
    private int titleTextColor;
    /**
     * 右边按钮文字的大小(sp)
     */
    private float menuTextSize;
    /**
     * 返回按钮(图片必须是正方形的,大于或者等于90像素)
     */
    private int backDrawable;

    public int getBackDrawable() {
        return backDrawable;
    }

    public void setBackDrawable(@DrawableRes int backDrawable) {
        this.backDrawable = backDrawable;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(@ColorRes int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public float getTitleTextSize() {
        return titleTextSize;
    }

    public void setTitleTextSize(float titleTextSize) {
        this.titleTextSize = titleTextSize;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(@ColorRes int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    public float getMenuTextSize() {
        return menuTextSize;
    }

    public void setMenuTextSize(float menuTextSize) {
        this.menuTextSize = menuTextSize;
    }
}
