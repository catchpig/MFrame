package com.zhuazhu.frame.mvp.contract;

import com.zhuazhu.frame.adpter.ReAdapter;

import mejust.frame.mvp.BaseContract;

/**
 * 创建时间:2017/12/22 0:07<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/22 0:07<br/>
 * 描述:
 */

public interface RecyclerContract {
    interface Model {
    }

    interface View extends BaseContract.View{
        void setAdapter(ReAdapter adapter);
    }

    interface Presenter extends BaseContract.Presenter {
        void refreshData();
        void addData();
    }
}
