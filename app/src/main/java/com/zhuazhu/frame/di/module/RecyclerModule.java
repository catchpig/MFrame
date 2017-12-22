package com.zhuazhu.frame.di.module;

import com.zhuazhu.frame.adpter.ReAdapter;
import com.zhuazhu.frame.mvp.contract.RecyclerContract;

import dagger.Module;
import dagger.Provides;
import mejust.frame.annotation.ActivityScope;
import mejust.frame.widget.refresh.IPageControl;

/**
 * 创建时间:2017/12/22 0:15<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/22 0:15<br/>
 * 描述:
 */
@ActivityScope
@Module
public class RecyclerModule {
    private RecyclerContract.View mView;
    private IPageControl mPageControl;
    public RecyclerModule(RecyclerContract.View view,IPageControl pageControl){
        mView = view;
        mPageControl = pageControl;
    }
    @Provides
    public RecyclerContract.View provideRecyclerView(){
        return mView;
    };
    @Provides
    public IPageControl providePageControl(){
        return mPageControl;
    }
    @Provides
    public ReAdapter provideReAdapter(IPageControl pageControl){
        return  new ReAdapter(pageControl);
    }
}
