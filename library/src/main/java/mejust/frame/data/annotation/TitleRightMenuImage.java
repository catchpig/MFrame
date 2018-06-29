package mejust.frame.data.annotation;

import android.support.annotation.DrawableRes;
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
public @interface TitleRightMenuImage {
    @DrawableRes int value() default -1;
}
