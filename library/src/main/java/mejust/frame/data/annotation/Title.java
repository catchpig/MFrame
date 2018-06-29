package mejust.frame.data.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标题栏注解
 *
 * @author zhuazhu
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Title {
    /**
     * 标题
     */
    String value() default "";

    /**
     * 背景颜色
     */
    int backgroundColorRes() default -1;

    /**
     * 设置标题栏左右间距
     */
    int titlePaddingSize() default -1;

    /**
     * 显示返回按钮
     */
    boolean showBackMenu() default true;

    /**
     * 标题文字主题设置
     */
    TitleMainTextStyle mainTextStyle() default @TitleMainTextStyle();

    /**
     * 右边菜单按钮
     */
    TitleRightMenu[] titleRightMenu() default {};
}
