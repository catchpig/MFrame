package mejust.frame.widget.refresh;

import java.util.List;

/**
 * 创建时间:2017/12/21  19:49<br/>
 * 创建人: 廖斌<br/>
 * 修改人: 廖斌<br/>
 * 修改时间: 2017/12/21  19:49<br/>
 * 描述: 页码关联
 */
public interface IPageControl {

    void resetPageIndex();

    void loadNextPageIndex();

    int getNextPageIndex();

    int getRefreshStates();

    void updateSuccess(List<?> list);

    void updateError();
}
