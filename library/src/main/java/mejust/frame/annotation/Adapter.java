package mejust.frame.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import mejust.frame.widget.adapter.BaseViewHolder;

/**
 * 创建时间:2017/12/21 21:55<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/21 21:55<br/>
 * 描述:RecyclerView.Adapter的layoutId,ViewHolder
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Adapter {
    @LayoutRes int layout();

    Class<? extends BaseViewHolder> holder();
}
