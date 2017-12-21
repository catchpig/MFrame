package mejust.frame.widget.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mejust.frame.R;
import mejust.frame.widget.RefreshLayout;

/**
 * 创建时间:2017/12/21  19:45<br/>
 * 创建人: 廖斌<br/>
 * 修改人: 廖斌<br/>
 * 修改时间: 2017/12/21  19:45<br/>
 * 描述: RecyclerViewAdapter基类
 */
public abstract class BaseRecyclerAdapter<T,VH extends BaseViewHolder> extends
        RecyclerView.Adapter<BaseViewHolder> implements IAdapterListControl<T>{

    /**
     * 点击事件方法
     */
    public interface OnItemClickListener {
        void itemClick(View view, int position);
    }

    /**
     * 数据源
     */
    private List<T> mData = new ArrayList<>();
    /**
     * layouts Id 列表
     */
    private SparseIntArray layouts = new SparseIntArray();
    /**
     * 页码接口
     */
    private IPageControl pageControl;
    /**
     * 点击事件
     */
    private OnItemClickListener mListener;

    /**
     * 空内容
     */
    private int mEmptyViewId = R.layout.view_load_empty;
    private boolean isFirstLoad = true;// 第一次加载时不显示
    /**
     * 是否展示空页面
     */
    private boolean showEmpty;

    /**
     * 无数据类型
     */
    public static final int TYPE_EMPTY = -3;

    /**
     * 找不到layoutId
     */
    public static final int TYPE_NOT_FOUND = -404;
    /**
     * 正常的item
     */
    public static final int TYPE_NORMAL = 101;

    protected LayoutInflater mInflater;

    public BaseRecyclerAdapter(Context context) {
        this(context,null);
    }

    public BaseRecyclerAdapter(Context context ,IPageControl pageControl) {
        this.pageControl = pageControl;
        mInflater = LayoutInflater.from(context);
        addItemType(TYPE_EMPTY, mEmptyViewId);
    }

    /**
     * 设置每行点击事件的监听
     *
     * @param itemClickListener
     */
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.mListener = itemClickListener;
    }

    /**
     * 获取数据源
     */
    public List<T> getData(){
        return mData;
    }
    /**
     * 获取某列数据源
     */
    public T get(int position){
        return mData.get(position);
    }

    /**
     * 清空数据
     */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * @param layoutId : resourceId
     */
    public void setEmptyView(@LayoutRes int layoutId){
        mEmptyViewId = layoutId;
    }


    /**
     * 添加布局
     * @param type
     * @param layoutResId
     */
    protected void addItemType(int type, @LayoutRes int layoutResId) {
        if (layouts == null) {
            layouts = new SparseIntArray();
        }
        layouts.put(type, layoutResId);
    }

    /**
     * 获取布局Id
     * @param viewType
     * @return
     */
    private @LayoutRes int getLayoutId(int viewType) {
        return layouts.get(viewType, TYPE_NOT_FOUND);
    }

    /**
     * 添加默认布局
     * @param layoutResId
     */
    protected void setDefaultViewTypeLayout(@LayoutRes int layoutResId) {
        addItemType(TYPE_NORMAL, layoutResId);
    }

    protected VH createViewHolderByLayoutId(ViewGroup parent, int layoutResId) {
        return createBaseViewHolder(getItemView(layoutResId, parent));
    }

    protected View getItemView(@LayoutRes int layoutResId, ViewGroup parent) {
        return mInflater.inflate(layoutResId, parent, false);
    }

    /**
     * if you want to use subclass of BaseViewHolder in the adapter,
     * you must override the method to create new ViewHolder.
     *
     * @param view view
     * @return new ViewHolder
     */
    @SuppressWarnings("unchecked")
    protected VH createBaseViewHolder(View view) {
        Class temp = getClass();
        Class z = null;
        while (z == null && null != temp) {
            z = getInstancedGenericKClass(temp);
            temp = temp.getSuperclass();
        }
        VH k;
        // 泛型擦除会导致z为null
        if (z == null) {
            k = (VH) new BaseViewHolder(view);
        } else {
            k = createGenericKInstance(z, view);
        }
        return k != null ? k :  (VH)new BaseViewHolder(view);
    }

    /**
     * try to create Generic VH instance
     *
     * @param z
     * @param view
     * @return
     */
    @SuppressWarnings("unchecked")
    private VH createGenericKInstance(Class z, View view) {
        try {
            Constructor constructor;
            // inner and unstatic class
            if (z.isMemberClass() && !Modifier.isStatic(z.getModifiers())) {
                constructor = z.getDeclaredConstructor(getClass(), View.class);
                constructor.setAccessible(true);
                return (VH) constructor.newInstance(this, view);
            } else {
                constructor = z.getDeclaredConstructor(View.class);
                constructor.setAccessible(true);
                return (VH) constructor.newInstance(view);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get generic parameter VH
     *
     * @param z
     * @return
     */
    private Class getInstancedGenericKClass(Class z) {
        Type type = z.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            for (Type temp : types) {
                if (temp instanceof Class) {
                    Class tempClass = (Class) temp;
                    if (BaseViewHolder.class.isAssignableFrom(tempClass)) {
                        return tempClass;
                    }
                } else if (temp instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) temp).getRawType();
                    if (rawType instanceof Class && BaseViewHolder.class.isAssignableFrom((Class<?>) rawType)) {
                        return (Class<?>) rawType;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 创建ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        switch (viewType){
            case TYPE_EMPTY:
                return new EmptyViewHolder(getItemView(getLayoutId(TYPE_EMPTY), parent));
            default:
                viewHolder = createViewHolderByLayoutId(parent,getLayoutId(viewType));
                break;
        }
        return viewHolder;
    }

    /**
     * 绑定ViewHolder和数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        //空页面状态,不需要设置holder的内容
        if(getItemViewType(position)==TYPE_EMPTY){
            //第一次加载数据,不展示空页面
            if(isFirstLoad){
                holder.itemView.setVisibility(View.INVISIBLE);
            }else{
                holder.itemView.setVisibility(View.VISIBLE);
            }
            return;
        }
        cover((VH) holder,position);
    }

    /**
     * 添加点击事件
     * @param holder
     * @param position
     */
    protected void cover(VH holder, int position){

        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.itemClick(view, position);
            }
        });
        T t = mData.get(position);

        bindViewHolder(holder,t,position);
    }

    /**
     * 绑定viewholder的数据
     *
     * @param holder
     * @param t
     * @param position
     */
    public abstract void bindViewHolder(VH holder, T t, int position);

    /**
     * item数量
     * @return
     */
    @Override
    public int getItemCount() {
        int size = mData == null ? 0 : mData.size();
        if(size==0){
            showEmpty = true;
            size = 1;
        }else{
            showEmpty = false;
        }
        return size;
    }

    /**
     * 显示全局视图
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager
                .LayoutParams && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p =
                    (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    /**
     * 如果多重布局，需重写getItemViewType
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if(position==0&&showEmpty){
//            当前数据空位,展示空页面
            return TYPE_EMPTY;
        }
        return TYPE_NORMAL;
    }

    /**
     * 设置数据源
     * @param list
     */
    @Override
    public void setList(List<T> list) {
        isFirstLoad = false;
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加数据源
     * @param list
     */
    @Override
    public void addList(List<T> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 自动更新列表集合，根据当前刷新状态判断进行刷新或加载操作
     *
     * @param list 更新数据集合
     */
    @Override
    public void autoUpdateList(List<T> list) {
        if (pageControl == null) {
            throw new IllegalStateException("pageControl must be set");
        }
        pageControl.updateSuccess(list);
        if ((list == null || list.isEmpty())) {
            return;
        }
        int states = pageControl.getRefreshStates();
        if (states == RefreshLayout.REFRESH_LOADING) {
            addList(list);
        } else if (states == RefreshLayout.REFRESH_REFRESHING) {
            setList(list);
        }
    }

    /**
     * 获取某列数据
     * @param index
     * @return
     */
    @Override
    public T getListItem(int index) {
        if (index < 0 || index > mData.size() - 1) {
            return null;
        }
        return mData.get(index);
    }

    /**
     * 空ViewHolder
     */
    public static class EmptyViewHolder extends BaseViewHolder {

        public EmptyViewHolder(View parent) {
            super(parent);
        }

    }
}
