package mejust.frame.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface TitleBarMenu {

    int location();

    int iconRes() default -1;

    String text() default "";

    int textColor() default 0;

    float textSize() default 0;
}
