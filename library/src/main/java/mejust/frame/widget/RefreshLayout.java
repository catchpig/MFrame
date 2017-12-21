package mejust.frame.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.util.List;
import mejust.frame.widget.adapter.IPageControl;

/**
 * 创建时间:2017/12/20 16:44<br/>
 * 创建人: 李涛<br/>
 * 修改人: 廖斌<br/>
 * 修改时间: 2017/12/21 20:10<br/>
 * 描述: 刷新界面
 */

public class RefreshLayout extends SmartRefreshLayout implements IPageControl {

    /**
     * 预显示界面索引
     */
    private static final int NONE_PRE_PAGE_INDEX = -1;
    /**
     * 未刷新状态
     */
    public static final int REFRESH_NORMAL = 0;
    /**
     * 正在加载状态
     */
    public static final int REFRESH_LOADING = 1;
    /**
     * 正在刷新状态
     */
    public static final int REFRESH_REFRESHING = 2;

    /**
     * 当前页面index
     */
    private int currentPageIndex;

    /**
     * 预加载页的index
     */
    private int prePageIndex;

    /**
     * 一页的条目，默认16
     */
    private int pageSize = 16;

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        currentPageIndex = 1;
        prePageIndex = NONE_PRE_PAGE_INDEX;
        setDisableContentWhenLoading(true);
        setDisableContentWhenRefresh(true);
    }

    /**
     * 列表更新成功
     *
     * @param list 更新数据集合
     */
    @Override
    public void updateSuccess(List<?> list) {
        if (isRefreshing()) {
            setLoadmoreFinished(false);
        } else if (isLoading()) {
            if ((list == null || list.isEmpty()) || list.size() < pageSize) {
                setLoadmoreFinished(true);
            }
        }
        updateCurrentPageIndex();
        finishUpdate(true);
    }

    /**
     * 列表更新失败
     */
    @Override
    public void updateError() {
        this.prePageIndex = -1;
        finishUpdate(false);
    }

    @Override
    public void resetPageIndex() {
        this.prePageIndex = 1;
    }

    @Override
    public void loadNextPageIndex() {
        this.prePageIndex = this.currentPageIndex + 1;
    }

    @Override
    public int getNextPageIndex() {
        return this.prePageIndex;
    }

    @Override
    public int getRefreshStates() {
        if (isRefreshing()) {
            return REFRESH_REFRESHING;
        } else if (isLoading()) {
            return REFRESH_LOADING;
        }
        return REFRESH_NORMAL;
    }

    /**
     * 结束刷新或加载状态
     *
     * @param success 状态成功或失败
     */
    private void finishUpdate(boolean success) {
        if (isRefreshing()) {
            finishRefresh(success);
        } else if (isLoading()) {
            finishLoadmore(success);
        }
    }

    /**
     * 当前页面索引更新
     */
    private void updateCurrentPageIndex() {
        this.currentPageIndex = this.prePageIndex;
        this.prePageIndex = NONE_PRE_PAGE_INDEX;
    }
}
