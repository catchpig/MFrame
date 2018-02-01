package com.zhuazhu.frame.di.module;

import com.zhuazhu.frame.adpter.ReAdapter;
import com.zhuazhu.frame.mvp.contract.RecyclerContract;

import dagger.Module;
import dagger.Provides;
import mejust.frame.di.module.ActivityModule;
import mejust.frame.widget.refresh.IPageControl;

/**
 * 创建时间:2017/12/22 0:15<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/22 0:15<br/>
 * 描述:
 */
@Module
public class RecyclerModule extends ActivityModule<RecyclerContract.View> {
    private IPageControl mPageControl;
    public RecyclerModule(RecyclerContract.View view,IPageControl pageControl){
        super(view);
        mPageControl = pageControl;
    }


    @Provides
    public IPageControl providePageControl(){
        return mPageControl;
    }
    @Provides
    public ReAdapter provideReAdapter(IPageControl pageControl){
        return  new ReAdapter(pageControl);
    }

}
