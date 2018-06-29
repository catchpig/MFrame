package mejust.frame.data.annotation;

import android.support.annotation.IdRes;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangpeifeng
 * @date 2018/06/29 10:31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TitleRightMenu {
    /**
     * view id
     */
    @IdRes int viewId();

    /**
     * 左间距
     */
    int marginLeft() default 10;

    /**
     * 右间距
     */
    int marginRight() default 10;

    /**
     * 图标菜单
     */
    TitleRightMenuImage menuImage() default @TitleRightMenuImage();

    /**
     * 文字菜单
     */
    TitleRightMenuText menuText() default @TitleRightMenuText();
}
