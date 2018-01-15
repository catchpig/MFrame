package mejust.frame.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import io.reactivex.disposables.CompositeDisposable;
import mejust.frame.mvp.BaseContract;

/**
 * 创建时间:2017-12-21 10:41<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 10:41<br/>
 * 描述:
 */

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter {

    protected final V mView;
    protected final CompositeDisposable disposable;

    public BasePresenter(@NonNull V view) {
        this.mView = view;
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        disposable.clear();
    }

    @Override
    public FragmentActivity getViewActivity() {
        return mView.getViewActivity();
    }
}
