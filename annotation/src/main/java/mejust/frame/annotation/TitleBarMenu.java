package mejust.frame.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间: 2018/04/12
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/12
 * 描述: <empty/>
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface TitleBarMenu {

    /**
     * 显示位置
     */
    int location();

    /**
     * 菜单图片
     */
    int iconRes() default -1;

    /**
     * 菜单文本内容
     */
    String text() default "";

    /**
     * 文字的颜色
     */
    int textColor() default 0;

    /**
     * 文字大小
     */
    float textSize() default 0;
}
