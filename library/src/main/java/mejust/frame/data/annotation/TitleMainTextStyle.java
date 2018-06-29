package mejust.frame.data.annotation;

import android.support.annotation.ColorRes;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangpeifeng
 * @date 2018/06/29 10:26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TitleMainTextStyle {

    /**
     * 标题文字颜色，不设置时值为-1
     */
    @ColorRes int textColor() default -1;

    /**
     * 标题文字大小，不设置时值为-1
     */
    int textSize() default -1;

    /**
     * 标题是否居中
     */
    boolean textCenter() default true;
}
