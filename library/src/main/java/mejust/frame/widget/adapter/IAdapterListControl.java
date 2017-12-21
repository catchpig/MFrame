package mejust.frame.widget.adapter;

import java.util.List;

/**
 * 创建时间:2017/12/21  19:49<br/>
 * 创建人: 廖斌<br/>
 * 修改人: 廖斌<br/>
 * 修改时间: 2017/12/21  19:49<br/>
 * 描述: 数据填充接口
 */
public interface IAdapterListControl<T> {

    void setList(List<T> list);

    void addList(List<T> list);

    void autoUpdateList(List<T> list);

    T getListItem(int index);
}
