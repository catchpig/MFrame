package mejust.frame.annotation;

import android.support.annotation.ColorRes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间:2017/12/23 21:25<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/23 21:25<br/>
 * 描述:titileBar的属性设置
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TitleBar {
    /**
     * 标题内容
     * @return
     */
    String value();

    /**
     * 隐藏返回键
     * @return
     */
    boolean hiddenBack() default false;

    /**
     * 返回按钮的文字
     * @return
     */
    String backText() default "";

    /**
     * 文字的颜色
     * @return
     */
    int textColor() default -1;

    /**
     * 背景色
     * @return
     */
    @ColorRes
    int background() default -1;
}
