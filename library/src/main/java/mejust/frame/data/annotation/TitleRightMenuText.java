package mejust.frame.data.annotation;

import android.support.annotation.ColorRes;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangpeifeng
 * @date 2018/06/29 10:39
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TitleRightMenuText {

    String value() default "";

    int textSize() default -1;

    @ColorRes int textColor() default -1;
}
