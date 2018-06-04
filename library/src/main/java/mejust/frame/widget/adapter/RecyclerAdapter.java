package mejust.frame.widget.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import conm.zhuazhu.common.utils.ListUtils;
import mejust.frame.R;
import mejust.frame.data.annotation.Adapter;
import mejust.frame.common.log.Logger;
import mejust.frame.widget.refresh.IPageControl;
import mejust.frame.widget.refresh.RefreshLayoutWrapper;

/**
 * 创建时间:2017/12/21  19:45<br/>
 * 创建人: 廖斌<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017年12月22日13:43:56<br/>
 * 描述: RecyclerAdapter基类
 */

public abstract class RecyclerAdapter<M, VH extends BaseViewHolder>
        extends RecyclerView.Adapter<BaseViewHolder> implements IAdapterListControl<M> {
    private static final String TAG = "RecyclerAdapter";
    private List<M> mData;
    /**
     * 头部类型
     */
    public static final int TYPE_HEADER = -1;
    /**
     * 底部类型
     */
    public static final int TYPE_FOOTER = -2;
    /**
     * 无数据类型
     */
    public static final int TYPE_EMPTY = -3;
    /**
     * 正常的item
     */
    public static final int TYPE_NORMAL = 0;
    /**
     * 头部
     */
    private View mHeaderView;

    /**
     * 底部
     */
    private View mFooterView;
    /**
     * 是否展示空页面
     */
    private boolean showEmpty;
    /**
     * 空页面layout
     */
    private int emptyLayout = R.layout.view_load_empty;
    /**
     * 是否是第一次加载数据
     */
    private boolean firstLoad = true;
    private RecyclerAdapter.OnItemClickListener mListener;
    /**
     * 分页处理器
     */
    private IPageControl mPageControl;

    public RecyclerAdapter() {
        this(null);
    }

    public RecyclerAdapter(IPageControl pageControl) {
        mData = new ArrayList<>();
        mPageControl = pageControl;
        annotationAdapter();
    }

    private Adapter mAdapterAnnotation;

    /**
     * 获取@Adapter注解
     */
    private void annotationAdapter() {
        Class cls = this.getClass();
        if (cls.isAnnotationPresent(Adapter.class)) {
            mAdapterAnnotation = this.getClass().getAnnotation(Adapter.class);
        }
    }

    /**
     * 设置空页面
     */
    public void setEmptyLayout(@LayoutRes int emptyLayout) {
        this.emptyLayout = emptyLayout;
    }

    /**
     * 添加头部
     */
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    /**
     * 获取头部
     */
    public View getHeaderView() {
        return mHeaderView;
    }

    /**
     * 添加底部
     */
    public void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    /**
     * 获取底部
     */
    public View getFooterView() {
        return mFooterView;
    }

    /**
     * 设置每行点击事件的监听
     */
    public void setOnItemClickListener(RecyclerAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public List<M> getData() {
        if (ListUtils.isEmpty(mData)) {
            throw new IllegalStateException("mData is empty(数据为空)");
        }
        return mData;
    }

    @Override
    public M get(int position) {
        if (position < 0 || position > (mData.size() - 1)) {
            throw new IllegalStateException("position必须大于0,且不能大于mData的个数");
        }
        if (ListUtils.isEmpty(mData)) {
            return null;
        }
        return mData.get(position);
    }

    /**
     * 设置list为这个list
     */
    @Override
    public void set(List<M> data) {
        firstLoad = false;
        if (data != null) {
            mData = data;
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * list中添加更多的数据
     */
    @Override
    public void add(List<M> data) {
        if (mData == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 自动更新列表集合，根据当前刷新状态判断进行刷新或加载操作
     *
     * @param list 更新数据集合
     */
    @Override
    public void autoUpdateList(List<M> list) {
        if (mPageControl == null) {
            throw new IllegalStateException("pageControl must be set");
        }
        mPageControl.updateSuccess(list);
        int states = mPageControl.getRefreshStates();
        if (states == RefreshLayoutWrapper.REFRESH_LOADING) {
            add(list);
        } else if (states == RefreshLayoutWrapper.REFRESH_REFRESHING) {
            set(list);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && showEmpty) {
            //当前数据空位,展示空页面
            return TYPE_EMPTY;
        }
        if (position == 0 && mHeaderView != null) {
            //当前view是头部信息
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1 && mFooterView != null) {
            //当前view是底部信息
            return TYPE_FOOTER;
        }

        return getCenterViewType(position);
    }

    /**
     * 标准的item的类型
     *
     * @return 返回参数不能小于0
     */
    @IntRange(from = 0)
    public int getCenterViewType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        int size = mData == null ? 0 : mData.size();
        if (mHeaderView != null) {
            //有头部,item的个数+1
            size++;
        }
        if (mFooterView != null) {
            //有底部,item的个数+1
            size++;
        }
        if (size == 0) {
            showEmpty = true;
            size = 1;
        } else {
            showEmpty = false;
        }
        return size;
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载头部信息
        if (TYPE_HEADER == viewType) {
            return new HeaderAndFooterViewHolder(mHeaderView);
        }
        //加载底部信息
        if (TYPE_FOOTER == viewType) {
            return new HeaderAndFooterViewHolder(mFooterView);
        }
        //加载空页面
        if (TYPE_EMPTY == viewType) {
            View v = inflate(emptyLayout, parent);
            return new HeaderAndFooterViewHolder(v);
        }
        //反射获取ViewHolder
        if (mAdapterAnnotation != null) {
            return reflectViewHolder(parent);
        }
        return createHolder(parent, viewType);
    }

    /**
     * 反射获得ViewHolder
     */
    @SuppressWarnings("unchecked")
    private VH reflectViewHolder(ViewGroup parent) {
        View v = inflate(mAdapterAnnotation.layout(), parent);
        Class<VH> c = (Class<VH>) mAdapterAnnotation.holder();
        VH holder = null;
        try {
            Constructor<VH> con = c.getConstructor(View.class);
            holder = con.newInstance(v);
        } catch (NoSuchMethodException e) {
            Logger.e(TAG,"检查ViewHolder类及构造函数是否是public");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return holder;
    }

    /**
     * 除头部和底部的ViewHolder的获取
     *
     * @param viewType holder的类型
     */
    protected VH createHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * 获取需要viewHolder的view
     *
     * @param layoutId 布局文件
     */
    protected View inflate(int layoutId, ViewGroup group) {
        LayoutInflater inflater = LayoutInflater.from(group.getContext());
        return inflater.inflate(layoutId, group, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        int index = position;
        if (mHeaderView != null) {
            //当前holder是头部就直接返回,不需要去设置viewholder的内容
            if (getItemViewType(position) == TYPE_HEADER) {
                return;
            } else {
                /*
                 * 有头部的情况,需要要减1,否则取item的数据会取到当前数据的下一条,
                 * 取出最后一条数据的时候,会报下标溢出
                 */
                index--;
            }
        }
        if (mFooterView != null) {
            //当前holder是底部就直接返回,不需要去设置viewholder的内容
            if (getItemViewType(position) == TYPE_FOOTER) {
                return;
            }
        }
        //空页面状态,不需要设置holder的内容
        if (getItemViewType(position) == TYPE_EMPTY) {
            //第一次加载数据,不展示空页面
            if (firstLoad) {
                holder.itemView.setVisibility(View.INVISIBLE);
            } else {
                holder.itemView.setVisibility(View.VISIBLE);
            }
            return;
        }
        final int finalIndex = index;
        if (mData == null || mData.isEmpty() || index < 0 || index > mData.size() - 1) {
            return;
        }
        M m = mData.get(index);
        //设置item的点击回调事件
        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.itemClick(mRecyclerView.getId(), m, finalIndex);
            }
        });

        bindViewHolder((VH) holder, m, position);
    }

    /**
     * 绑定viewHolder的数据
     * @param holder
     * @param m
     * @param position 下标
     */
    public abstract void bindViewHolder(VH holder, M m, int position);

    private RecyclerView mRecyclerView;

    /**
     * 处理RecyclerView.LayoutManager是GridLayoutManager类型,
     * 对头部和底部实体进行一个处理,使其占满一行
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (getItemViewType(position) == TYPE_HEADER
                            || getItemViewType(position) == TYPE_FOOTER)
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 加入针对StaggeredGridLayoutManager跨列处理的代码
     * 一个item通过adapter开始显示会被回调
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p =
                    (StaggeredGridLayoutManager.LayoutParams) lp;
            //占满一行
            p.setFullSpan(true);
        }
    }

    /**
     * 头部和底部的ViewHolder
     */
    static class HeaderAndFooterViewHolder extends BaseViewHolder {

        public HeaderAndFooterViewHolder(View itemView) {
            super(itemView, false);
        }
    }

    /**
     * item点击事件
     */
    public interface OnItemClickListener<M> {
        /**
         * @param id RecyclerView.getId()
         * @param m item下的实体
         * @param position item所在的位置
         */
        void itemClick(@IdRes int id, M m, int position);
    }
}
