package mejust.frame.data.annotation;

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
public @interface StatusBarConfig {

    /**
     * 是否使用沉浸式布局,Activity中默认为初始化
     */
    boolean isInitActivity() default true;

    /**
     * 是否使用沉浸式布局,在Fragment中默认为不初始化操作
     */
    boolean isInitFragment() default false;

    /**
     * 是否设置状态栏字体灰色
     */
    boolean isDarkStatus() default false;

    /**
     * 用于fragment中，用于判断fragment显示是否是通过show()和hide()形式使用
     */
    boolean isHideStatus() default true;
}
