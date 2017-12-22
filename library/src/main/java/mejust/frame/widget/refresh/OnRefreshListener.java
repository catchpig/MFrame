package mejust.frame.widget.refresh;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

/**
 * 创建时间:2017/12/20 18:13<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/20 18:13<br/>
 * 描述:下拉刷新和上拉加载更多的封装类
 */

public abstract class OnRefreshListener implements OnRefreshLoadmoreListener {
  /**
   * 下拉刷新
   * @param refreshlayout
   */
  @Override
  public void onRefresh(RefreshLayout refreshlayout) {
    RefreshLayoutWrapper refreshLayoutWrapper = (RefreshLayoutWrapper) refreshlayout;
    //将页码变为1
    refreshLayoutWrapper.resetPageIndex();
    update(refreshLayoutWrapper);
  }

  /**
   *  上拉加载更多
   * @param refreshlayout
   */
  @Override
  public void onLoadmore(RefreshLayout refreshlayout) {
    RefreshLayoutWrapper refreshLayoutWrapper = (RefreshLayoutWrapper) refreshlayout;
    //请求列表的参数页码加1
    refreshLayoutWrapper.loadNextPageIndex();
    update(refreshLayoutWrapper);
  }

  /**
   * 下拉刷新和上拉加载更多的回调接口
   * @param refreshLayout
   */
  public abstract void update(RefreshLayoutWrapper refreshLayout);
}