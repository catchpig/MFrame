package mejust.frame.data.annotation;

import android.support.annotation.DrawableRes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标题栏注解
 * @author zhuazhu
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Title {
    /**
     * 标题
     * @return
     */
    String value() default "";

    /**
     * 右边第一个按钮的文字<br/>
     * 对应的id{@link mejust.frame.R.id.rightFirstText}
     * @return
     */
    String rightFirstText() default "";

    /**
     * 右边第一个按钮的文字<br/>
     * 对应的id{@link mejust.frame.R.id.rightSecondText}
     * @return
     */
    String rightSecondText() default "";
    /**
     * 右边第一个按钮的文字<br/>
     * 对应的id{@link mejust.frame.R.id.rightFirstDrawable}
     * @return
     */
    @DrawableRes
    int rightFirstDrawable() default -1;

    /**
     * 右边第一个按钮的文字<br/>
     * 对应的id{@link mejust.frame.R.id.rightSecondDrawable}
     * @return
     */
    @DrawableRes
    int rightSecondDrawable() default -1;

}
