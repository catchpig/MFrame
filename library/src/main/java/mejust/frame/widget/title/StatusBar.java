package mejust.frame.widget.title;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import conm.zhuazhu.common.utils.Utils;

/**
 * 创建时间: 2018/02/05 11:08
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/02/05 11:08
 * 描述: 状态栏操作封装
 */
public final class StatusBar {

    private ImmersionBar immersionBar;

    public static StatusBar with(@NonNull Activity activity) {
        return new StatusBar(activity);
    }

    public static StatusBar with(@NonNull Activity activity, @NonNull Fragment fragment) {
        return new StatusBar(activity, fragment);
    }

    public static StatusBar with(@NonNull DialogFragment dialogFragment, @NonNull Dialog dialog) {
        return new StatusBar(dialogFragment, dialog);
    }

    public static StatusBar with(@NonNull Activity activity, @NonNull Dialog dialog,
            @NonNull String dialogTag) {
        return new StatusBar(activity, dialog, dialogTag);
    }

    private StatusBar(Activity activity) {
        immersionBar = ImmersionBar.with(activity);
    }

    private StatusBar(Activity activity, Fragment fragment) {
        immersionBar = ImmersionBar.with(activity, fragment);
    }

    private StatusBar(DialogFragment dialogFragment, Dialog dialog) {
        immersionBar = ImmersionBar.with(dialogFragment, dialog);
    }

    private StatusBar(Activity activity, Dialog dialog, String dialogTag) {
        immersionBar = ImmersionBar.with(activity, dialog, dialogTag);
    }

    public void init() {
        immersionBar.init();
    }

    /**
     * 当Activity/Fragment/Dialog关闭的时候调用
     */
    public void destroy() {
        immersionBar.destroy();
    }

    /**
     * 透明状态栏，默认透明
     */
    public StatusBar transparentStatusBar() {
        immersionBar = immersionBar.transparentStatusBar();
        return this;
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @return the immersion bar
     */
    public StatusBar statusBarColor(@ColorRes int statusBarColor) {
        return this.statusBarColorInt(ContextCompat.getColor(Utils.getApp(), statusBarColor));
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @param alpha the alpha  透明度
     */
    public StatusBar statusBarColor(@ColorRes int statusBarColor,
            @FloatRange(from = 0f, to = 1f) float alpha) {
        return this.statusBarColorInt(ContextCompat.getColor(Utils.getApp(), statusBarColor),
                alpha);
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @param statusBarColorTransform the status bar color transform 状态栏变换后的颜色
     * @param alpha the alpha  透明度
     * @return the immersion bar
     */
    public StatusBar statusBarColor(@ColorRes int statusBarColor,
            @ColorRes int statusBarColorTransform, @FloatRange(from = 0f, to = 1f) float alpha) {
        return this.statusBarColorInt(ContextCompat.getColor(Utils.getApp(), statusBarColor),
                ContextCompat.getColor(Utils.getApp(), statusBarColorTransform), alpha);
    }

    /**
     * 状态栏颜色
     * Status bar color int immersion bar.
     *
     * @param statusBarColor the status bar color
     * @return the immersion bar
     */
    public StatusBar statusBarColor(String statusBarColor) {
        return this.statusBarColorInt(Color.parseColor(statusBarColor));
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色
     * @param alpha the alpha  透明度
     * @return the immersion bar
     */
    public StatusBar statusBarColor(String statusBarColor,
            @FloatRange(from = 0f, to = 1f) float alpha) {
        return this.statusBarColorInt(Color.parseColor(statusBarColor), alpha);
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色
     * @param statusBarColorTransform the status bar color transform 状态栏变换后的颜色
     * @param alpha the alpha  透明度
     * @return the immersion bar
     */
    public StatusBar statusBarColor(String statusBarColor, String statusBarColorTransform,
            @FloatRange(from = 0f, to = 1f) float alpha) {
        return this.statusBarColorInt(Color.parseColor(statusBarColor),
                Color.parseColor(statusBarColorTransform), alpha);
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @return the immersion bar
     */
    public StatusBar statusBarColorInt(@ColorInt int statusBarColor) {
        immersionBar = immersionBar.statusBarColorInt(statusBarColor);
        return this;
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @param alpha the alpha  透明度
     * @return the immersion bar
     */
    public StatusBar statusBarColorInt(@ColorInt int statusBarColor,
            @FloatRange(from = 0f, to = 1f) float alpha) {
        immersionBar = immersionBar.statusBarColorInt(statusBarColor, alpha);
        return this;
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @param statusBarColorTransform the status bar color transform 状态栏变换后的颜色
     * @param alpha the alpha  透明度
     * @return the immersion bar
     */
    public StatusBar statusBarColorInt(@ColorInt int statusBarColor,
            @ColorInt int statusBarColorTransform, @FloatRange(from = 0f, to = 1f) float alpha) {
        immersionBar =
                immersionBar.statusBarColorInt(statusBarColor, statusBarColorTransform, alpha);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view the view
     * @return the immersion bar
     */
    public StatusBar addViewSupportTransformColor(View view) {
        immersionBar = immersionBar.addViewSupportTransformColor(view);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view the view
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    public StatusBar addViewSupportTransformColor(View view,
            @ColorRes int viewColorAfterTransform) {
        immersionBar = immersionBar.addViewSupportTransformColor(view, viewColorAfterTransform);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view the view
     * @param viewColorBeforeTransform the view color before transform
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    public StatusBar addViewSupportTransformColor(View view, @ColorRes int viewColorBeforeTransform,
            @ColorRes int viewColorAfterTransform) {
        immersionBar = immersionBar.addViewSupportTransformColor(view, viewColorBeforeTransform,
                viewColorAfterTransform);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view the view
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    public StatusBar addViewSupportTransformColor(View view, String viewColorAfterTransform) {
        return this.addViewSupportTransformColorInt(view,
                Color.parseColor(viewColorAfterTransform));
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view the view
     * @param viewColorBeforeTransform the view color before transform
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    public StatusBar addViewSupportTransformColor(View view, String viewColorBeforeTransform,
            String viewColorAfterTransform) {
        return this.addViewSupportTransformColorInt(view,
                Color.parseColor(viewColorBeforeTransform),
                Color.parseColor(viewColorAfterTransform));
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view the view
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    public StatusBar addViewSupportTransformColorInt(View view,
            @ColorInt int viewColorAfterTransform) {
        immersionBar = immersionBar.addViewSupportTransformColorInt(view, viewColorAfterTransform);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view the view
     * @param viewColorBeforeTransform the view color before transform
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    public StatusBar addViewSupportTransformColorInt(View view,
            @ColorInt int viewColorBeforeTransform, @ColorInt int viewColorAfterTransform) {
        immersionBar = immersionBar.addViewSupportTransformColorInt(view, viewColorBeforeTransform,
                viewColorAfterTransform);
        return this;
    }

    /**
     * view透明度
     * View alpha immersion bar.
     *
     * @param viewAlpha the view alpha
     * @return the immersion bar
     */
    public StatusBar viewAlpha(@FloatRange(from = 0f, to = 1f) float viewAlpha) {
        immersionBar = immersionBar.viewAlpha(viewAlpha);
        return this;
    }

    /**
     * Remove support view immersion bar.
     *
     * @param view the view
     * @return the immersion bar
     */
    public StatusBar removeSupportView(View view) {
        immersionBar = immersionBar.removeSupportView(view);
        return this;
    }

    /**
     * Remove support all view immersion bar.
     *
     * @return the immersion bar
     */
    public StatusBar removeSupportAllView() {
        immersionBar = immersionBar.removeSupportAllView();
        return this;
    }

    /**
     * 有导航栏的情况下，Activity是否全屏显示
     *
     * @param isFullScreen the is full screen
     * @return the immersion bar
     */
    public StatusBar fullScreen(boolean isFullScreen) {
        immersionBar = immersionBar.fullScreen(isFullScreen);
        return this;
    }

    /**
     * 状态栏透明度
     *
     * @param statusAlpha the status alpha
     * @return the immersion bar
     */
    public StatusBar statusBarAlpha(@FloatRange(from = 0f, to = 1f) float statusAlpha) {
        immersionBar = immersionBar.statusBarAlpha(statusAlpha);
        return this;
    }

    /**
     * 状态栏字体深色或亮色
     *
     * @param isDarkFont true 深色
     * @return the immersion bar
     */
    public StatusBar statusBarDarkFont(boolean isDarkFont) {
        immersionBar = immersionBar.statusBarDarkFont(isDarkFont);
        return this;
    }

    /**
     * 状态栏字体深色或亮色，判断设备支不支持状态栏变色来设置状态栏透明度
     * Status bar dark font immersion bar.
     *
     * @param isDarkFont the is dark font
     * @param statusAlpha the status alpha 如果不支持状态栏字体变色可以使用statusAlpha来指定状态栏透明度，比如白色状态栏的时候可以用到
     * @return the immersion bar
     */
    public StatusBar statusBarDarkFont(boolean isDarkFont,
            @FloatRange(from = 0f, to = 1f) float statusAlpha) {
        immersionBar = immersionBar.statusBarDarkFont(isDarkFont, statusAlpha);
        return this;
    }

    /**
     * 隐藏导航栏或状态栏
     *
     * @param barHide the bar hide
     * @return the immersion bar
     */
    public StatusBar hideBar(BarHide barHide) {
        immersionBar = immersionBar.hideBar(barHide);
        return this;
    }

    /**
     * 解决布局与状态栏重叠问题
     *
     * @param fits the fits
     * @return the immersion bar
     */
    public StatusBar fitsSystemWindows(boolean fits) {
        immersionBar = immersionBar.fitsSystemWindows(fits);
        return this;
    }

    /**
     * 解决布局与状态栏重叠问题，支持侧滑返回
     * Fits system windows immersion bar.
     *
     * @param fits the fits
     * @param statusBarColorContentView the status bar color content view  状态栏颜色
     * @return the immersion bar
     */
    public StatusBar fitsSystemWindows(boolean fits, @ColorRes int statusBarColorContentView) {
        immersionBar = immersionBar.fitsSystemWindows(fits, statusBarColorContentView);
        return this;
    }

    /**
     * 解决布局与状态栏重叠问题，支持侧滑返回
     * Fits system windows immersion bar.
     *
     * @param fits the fits
     * @param statusBarColorContentView the status bar color content view 状态栏颜色
     * @param statusBarColorContentViewTransform the status bar color content view transform
     * 状态栏变色后的颜色
     * @param statusBarContentViewAlpha the status bar content view alpha  透明度
     * @return the immersion bar
     */
    public StatusBar fitsSystemWindows(boolean fits, @ColorRes int statusBarColorContentView,
            @ColorRes int statusBarColorContentViewTransform,
            @FloatRange(from = 0f, to = 1f) float statusBarContentViewAlpha) {
        immersionBar = immersionBar.fitsSystemWindows(fits, statusBarColorContentView,
                statusBarColorContentViewTransform, statusBarContentViewAlpha);
        return this;
    }

    /**
     * 通过状态栏高度动态设置状态栏布局
     *
     * @param view the view
     * @return the immersion bar
     */
    public StatusBar statusBarView(View view) {
        immersionBar = immersionBar.statusBarView(view);
        return this;
    }

    /**
     * 通过状态栏高度动态设置状态栏布局,只能在Activity中使用
     *
     * @param viewId the view id
     * @return the immersion bar
     */
    public StatusBar statusBarView(@IdRes int viewId) {
        immersionBar = immersionBar.statusBarView(viewId);
        return this;
    }

    /**
     * 通过状态栏高度动态设置状态栏布局
     * Status bar view immersion bar.
     *
     * @param viewId the view id
     * @param rootView the root view
     * @return the immersion bar
     */
    public StatusBar statusBarView(@IdRes int viewId, View rootView) {
        immersionBar = immersionBar.statusBarView(viewId, rootView);
        return this;
    }

    /**
     * 支持有actionBar的界面,调用该方法，布局讲从actionBar下面开始绘制
     * Support action bar immersion bar.
     *
     * @param isSupportActionBar the is support action bar
     * @return the immersion bar
     */
    public StatusBar supportActionBar(boolean isSupportActionBar) {
        immersionBar = immersionBar.supportActionBar(isSupportActionBar);
        return this;
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法
     * Title bar immersion bar.
     *
     * @param view the view
     * @return the immersion bar
     */
    public StatusBar titleBar(View view) {
        return titleBar(view, true);
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法
     * Title bar immersion bar.
     *
     * @param view the view
     * @param statusBarFlag the status bar flag 默认为true false表示状态栏不支持变色，true表示状态栏支持变色
     * @return the immersion bar
     */
    public StatusBar titleBar(View view, boolean statusBarFlag) {
        immersionBar = immersionBar.titleBar(view, statusBarFlag);
        return this;
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法，只支持Activity
     * Title bar immersion bar.
     *
     * @param viewId the view id
     * @return the immersion bar
     */
    public StatusBar titleBar(@IdRes int viewId) {
        immersionBar = immersionBar.titleBar(viewId);
        return this;
    }

    /**
     * Title bar immersion bar.
     *
     * @param viewId the view id
     * @param statusBarFlag the status bar flag
     * @return the immersion bar
     */
    public StatusBar titleBar(@IdRes int viewId, boolean statusBarFlag) {
        immersionBar = immersionBar.titleBar(viewId, statusBarFlag);
        return this;
    }

    /**
     * Title bar immersion bar.
     *
     * @param viewId the view id
     * @param rootView the root view
     * @return the immersion bar
     */
    public StatusBar titleBar(@IdRes int viewId, View rootView) {
        immersionBar = immersionBar.titleBar(viewId, rootView);
        return this;
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法，支持任何view
     * Title bar immersion bar.
     *
     * @param viewId the view id
     * @param rootView the root view
     * @param statusBarFlag the status bar flag 默认为true false表示状态栏不支持变色，true表示状态栏支持变色
     * @return the immersion bar
     */
    public StatusBar titleBar(@IdRes int viewId, View rootView, boolean statusBarFlag) {
        immersionBar = immersionBar.titleBar(viewId, rootView, statusBarFlag);
        return this;
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param viewId the view id   标题栏资源id
     * @return the immersion bar
     */
    public StatusBar titleBarMarginTop(@IdRes int viewId) {
        immersionBar = immersionBar.titleBarMarginTop(viewId);
        return this;
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param viewId the view id  标题栏资源id
     * @param rootView the root view  布局view
     * @return the immersion bar
     */
    public StatusBar titleBarMarginTop(@IdRes int viewId, View rootView) {
        return titleBarMarginTop(rootView.findViewById(viewId));
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param view the view  要改变的标题栏view
     * @return the immersion bar
     */
    public StatusBar titleBarMarginTop(View view) {
        immersionBar = immersionBar.titleBarMarginTop(view);
        return this;
    }

    /**
     * Status bar color transform enable immersion bar.
     *
     * @param statusBarFlag the status bar flag
     * @return the immersion bar
     */
    public StatusBar statusBarColorTransformEnable(boolean statusBarFlag) {
        immersionBar = immersionBar.statusBarColorTransformEnable(statusBarFlag);
        return this;
    }

    /**
     * 透明状态栏和导航栏
     * @return
     */
    public StatusBar transparentBar(){
        immersionBar = immersionBar.transparentBar();
        return this;
    }

    /**
     * 一键重置所有参数
     * Reset immersion bar.
     *
     * @return the immersion bar
     */
    public StatusBar reset() {
        immersionBar = immersionBar.reset();
        return this;
    }

    /**
     * 给某个页面设置tag来标识这页bar的属性.
     * Add tag bar tag.
     *
     * @param tag the tag
     * @return the bar tag
     */
    public StatusBar addTag(String tag) {
        immersionBar = immersionBar.addTag(tag);
        return this;
    }

    /**
     * 根据tag恢复到某次调用时的参数
     * Recover immersion bar.
     *
     * @param tag the tag
     * @return the immersion bar
     */
    public StatusBar getTag(String tag) {
        immersionBar = immersionBar.getTag(tag);
        return this;
    }

    /**
     * 解决软键盘与底部输入框冲突问题 ，默认是false
     * Keyboard enable immersion bar.
     *
     * @param enable the enable
     * @return the immersion bar
     */
    public StatusBar keyboardEnable(boolean enable) {
        immersionBar = immersionBar.keyboardEnable(enable);
        return this;
    }

    /**
     * 解决软键盘与底部输入框冲突问题 ，默认是false
     *
     * @param enable the enable
     * @param keyboardMode the keyboard mode
     * @return the immersion bar
     */
    public StatusBar keyboardEnable(boolean enable, int keyboardMode) {
        immersionBar = immersionBar.keyboardEnable(enable, keyboardMode);
        return this;
    }

    /**
     * 修改键盘模式
     * Keyboard mode immersion bar.
     *
     * @param keyboardMode the keyboard mode
     * @return the immersion bar
     */
    public StatusBar keyboardMode(int keyboardMode) {
        immersionBar = immersionBar.keyboardMode(keyboardMode);
        return this;
    }

    /**
     * 软键盘弹出关闭的回调监听
     * Sets on keyboard listener.
     *
     * @param onKeyboardListener the on keyboard listener
     * @return the on keyboard listener
     */
    public StatusBar setOnKeyboardListener(OnKeyboardListener onKeyboardListener) {
        immersionBar = immersionBar.setOnKeyboardListener(onKeyboardListener);
        return this;
    }

    /**
     * 是否可以修改导航栏颜色，默认为true
     * Navigation bar enable immersion bar.
     *
     * @param navigationBarEnable the enable
     * @return the immersion bar
     */
    public StatusBar navigationBarEnable(boolean navigationBarEnable) {
        immersionBar = immersionBar.navigationBarEnable(navigationBarEnable);
        return this;
    }

    /**
     * 是否可以修改4.4设备导航栏颜色，默认为true
     *
     * @param navigationBarWithKitkatEnable the navigation bar with kitkat enable
     * @return the immersion bar
     */
    public StatusBar navigationBarWithKitkatEnable(boolean navigationBarWithKitkatEnable) {
        immersionBar = immersionBar.navigationBarWithKitkatEnable(navigationBarWithKitkatEnable);
        return this;
    }
}
