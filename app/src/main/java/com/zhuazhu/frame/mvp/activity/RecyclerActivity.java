package com.zhuazhu.frame.mvp.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.zhuazhu.frame.R;
import com.zhuazhu.frame.adpter.ReAdapter;
import com.zhuazhu.frame.di.module.RecyclerModuleBase;
import com.zhuazhu.frame.mvp.application.FrameApplication;
import com.zhuazhu.frame.mvp.contract.RecyclerContract;
import com.zhuazhu.frame.mvp.presenter.RecyclerPresenterImp;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.TitleBarConfig;
import mejust.frame.mvp.view.BasePresenterActivity;
import mejust.frame.widget.divider.SpacesItemDecoration;
import mejust.frame.widget.refresh.OnRefreshListener;
import mejust.frame.widget.refresh.RefreshLayoutWrapper;

/**
 * 创建时间:2017/12/21 23:45<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/21 23:45<br/>
 * 描述:
 */
@LayoutId(R.layout.activity_recycler)
@TitleBarConfig(value = "列表", backgroundColor = R.color.colorAccent)
public class RecyclerActivity extends BasePresenterActivity<RecyclerPresenterImp>
        implements RecyclerContract.View {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refresh)
    RefreshLayoutWrapper mRefresh;

    @Override
    protected void initParam() {

    }

    @Override
    protected void injectComponent() {
        FrameApplication.getAppComponent()
                .recyclerComponent(new RecyclerModuleBase(this, mRefresh))
                .inject(this);
    }

    @Override
    protected void initView() {
        mRefresh.setOnRefreshLoadmoreListener(new OnRefreshListener() {
            @Override
            public void update(RefreshLayoutWrapper refreshLayout) {
                mPresenter.refreshData();
            }
        });
        mRefresh.autoRefresh();
    }

    @Override
    public void setAdapter(ReAdapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SpacesItemDecoration decoration = new SpacesItemDecoration(0, 1, R.color.white);
        mRecycler.addItemDecoration(decoration);
        mRecycler.setLayoutManager(linearLayoutManager);
        mRecycler.setAdapter(adapter);
    }

    @OnClick(R.id.button_ss)
    public void clickSS(View view) {
        System.out.println("fff");
    }
}
