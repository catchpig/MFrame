package com.zhuazhu.frame.mvp.presenter;

import android.support.annotation.NonNull;
import com.zhuazhu.frame.data.HttpHelper;
import com.zhuazhu.frame.mvp.contract.MainContract;
import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import mejust.frame.mvp.presenter.BasePresenter;
import mejust.frame.net.FlowableUtils;
import mejust.frame.utils.log.Logger;

/**
 * 创建时间:2017-12-21 16:39<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 16:39<br/>
 * 描述:
 */

public class MainPresenterImp extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {

    private final HttpHelper httpHelper;

    @Inject
    public MainPresenterImp(@NonNull MainContract.View view, HttpHelper httpHelper) {
        super(view);
        this.httpHelper = httpHelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //AjaxResult<String> result = new AjaxResult<>();
        //result.setCode("200");
        //execute(FlowableUtils.create(Flowable.just(result)), new Callback<String>() {
        //    @Override
        //    public void success(String s) {
        //        Logger.i("MainPresenterImp", s);
        //    }
        //});
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void checkLoading() {
        mView.showLoading(true);
        disposable.add(Flowable.timer(2000, TimeUnit.MILLISECONDS)
                .flatMap(aLong -> {
                    mView.hideLoading();
                    return Flowable.just(String.valueOf(aLong));
                })
                .delay(2000, TimeUnit.MILLISECONDS)
                .map(s -> {
                    mView.showLoading(true);
                    return s;
                })
                .delay(2000, TimeUnit.MILLISECONDS)
                .compose(FlowableUtils.rxSchedulerHelper())
                .subscribeWith(new ResourceSubscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        Logger.i(s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t);
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                        Logger.i("onComplete");
                    }
                }));
    }
}
