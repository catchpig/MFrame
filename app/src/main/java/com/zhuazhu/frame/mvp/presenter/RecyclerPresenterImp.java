package com.zhuazhu.frame.mvp.presenter;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.zhuazhu.frame.adpter.ReAdapter;
import com.zhuazhu.frame.mvp.contract.RecyclerContract;
import com.zhuazhu.frame.mvp.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mejust.frame.mvp.presenter.BasePresenter;
import mejust.frame.widget.adapter.RecyclerAdapter;
import mejust.frame.widget.refresh.IPageControl;

/**
 * 创建时间:2017/12/22 0:07<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/22 0:07<br/>
 * 描述:
 */

public class RecyclerPresenterImp extends BasePresenter<RecyclerContract.View>
        implements RecyclerContract.Presenter,RecyclerAdapter.OnItemClickListener<User> {

    private ReAdapter mAdapter;
    private IPageControl mPageControl;

    @Inject
    public RecyclerPresenterImp(@NonNull RecyclerContract.View view, ReAdapter adapter,
            IPageControl pageControl) {
        super(view);
        mAdapter = adapter;
        mPageControl = pageControl;
    }

    @Override
    public void onCreate() {
        mView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void refreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.autoUpdateList(users());
            }
        }, 2000);
    }

    @Override
    public void addData() {
        mAdapter.add(users());
    }

    public List<User> users() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            User user = new User();
            user.setId(i);
            user.setName("名字" + i);
            list.add(user);
        }
        return list;
    }

    @Override
    public void itemClick(int id, User user, int position) {
        user.setId(position);
        user.setName("我是第"+position+"行,我被点击了");
    }
}
