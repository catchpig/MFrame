package mejust.frame.widget.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 创建时间:2017/12/21  19:45<br/>
 * 创建人: 廖斌<br/>
 * 修改人: 廖斌<br/>
 * 修改时间: 2017/12/21  19:45<br/>
 * 描述: ViewHolder基类
 */
public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public T t;

    /**
     * 是否用dataBanding
     * @param v
     * @param isBinding
     */
    public BaseViewHolder(View v,boolean isBinding){
        super(v);
        if(isBinding){
            t = DataBindingUtil.bind(v);
        }
    }
    public BaseViewHolder(View v) {
        this(v,true);
    }
}
