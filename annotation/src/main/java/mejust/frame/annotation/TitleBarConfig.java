package mejust.frame.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface TitleBarConfig {
    /**
     * title content
     */
    String value();

    /**
     * title textColor
     */
    int textColor() default 0;

    /**
     * title textSize
     */
    float textSize() default 0;

    /**
     * title background
     */
    int backgroundColor() default 0;

    /**
     * title visible
     */
    boolean visible() default true;
}
