package mejust.frame.annotation;

import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间:2017/12/25 12:31<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/25 12:31<br/>
 * 描述:状态栏注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StatusBar {
    /**
     * 颜色值
     * @return
     */
    @ColorRes
    int value() default -1;

    /**
     * 状态栏变换后的颜色
     * @return
     */
    int statusBarColorTransform() default -1;

    /**
     * flyme OS状态栏字体颜色
     * @return
     */
    int flymeOSStatusBarFontColor() default -1;

    /**
     * 状态栏字体是深色，不写默认为亮色
     * @return
     */
    boolean statusBarDarkFont() default false;
    /**
     * 透明
     * @return
     */
    boolean translucent() default false;

    /**
     * 隐藏状态栏背景
     * @return
     */
    boolean hidden() default false;

    /**
     * 透明度
     * @return
     */
    @FloatRange(from = 0,to = 1)
    float alpha() default 0;
}
