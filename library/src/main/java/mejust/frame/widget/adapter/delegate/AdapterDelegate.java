package mejust.frame.widget.adapter.delegate;

import mejust.frame.widget.adapter.BaseViewHolder;

/**
 * 创建时间:2017/12/22 11:16<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/22 11:16<br/>
 * 描述:
 */

public interface AdapterDelegate<T> {
    void getLayoutId();
    void bindView(BaseViewHolder holder,T data,int postion);
}
