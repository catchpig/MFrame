package mejust.frame.annotation;

import android.support.annotation.DrawableRes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间:2017/12/23 21:49<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/23 21:49<br/>
 * 描述:右边边第二个图片的事件监听注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImageRightSecondEvent {
    /**
     * 按钮的图片的资源id
     * @return
     */
    @DrawableRes
    int value();
}
