package mejust.frame.annotation.options;

import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;

/**
 * 创建时间:2018/2/3 22:21<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2018/2/3 22:21<br/>
 * 描述:
 */

public class StatusBarOption {
    /**
     * 状态栏颜色
     */
    private int statusBarColor =-1;
    /**
     * 状态栏变换后的颜色
     */
    private int statusBarColorTransform = -1;
    /**
     * flyme OS状态栏字体颜色
     */
    private int flymeOSStatusBarFontColor = -1;
    /**
     * 透明度
     */
    private float alpha;
    /**
     * 状态栏字体是深色，不写默认为亮色
     */
    private boolean statusBarDarkFont;
    /**
     * 透明
     */
    private boolean translucent;
    /**
     * 隐藏状态栏
     */
    private boolean hidden;

    public StatusBarOption(){

    }

    public StatusBarOption(int statusBarColor, int statusBarColorTransform, int flymeOSStatusBarFontColor, float alpha, boolean statusBarDarkFont, boolean translucent, boolean hidden) {
        this.statusBarColor = statusBarColor;
        this.statusBarColorTransform = statusBarColorTransform;
        this.flymeOSStatusBarFontColor = flymeOSStatusBarFontColor;
        this.alpha = alpha;
        this.statusBarDarkFont = statusBarDarkFont;
        this.translucent = translucent;
        this.hidden = hidden;
    }

    public int getStatusBarColor() {
        return statusBarColor;
    }

    public void setStatusBarColor(@ColorRes int statusBarColor) {
        this.statusBarColor = statusBarColor;
    }

    public int getStatusBarColorTransform() {
        return statusBarColorTransform;
    }

    public void setStatusBarColorTransform(@ColorRes int statusBarColorTransform) {
        this.statusBarColorTransform = statusBarColorTransform;
    }

    public int getFlymeOSStatusBarFontColor() {
        return flymeOSStatusBarFontColor;
    }

    public void setFlymeOSStatusBarFontColor(@ColorRes int flymeOSStatusBarFontColor) {
        this.flymeOSStatusBarFontColor = flymeOSStatusBarFontColor;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(@FloatRange(from = 0,to = 1) float alpha) {
        this.alpha = alpha;
    }

    public boolean isStatusBarDarkFont() {
        return statusBarDarkFont;
    }

    public void setStatusBarDarkFont(boolean statusBarDarkFont) {
        this.statusBarDarkFont = statusBarDarkFont;
    }

    public boolean isTranslucent() {
        return translucent;
    }

    public void setTranslucent(boolean translucent) {
        this.translucent = translucent;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
