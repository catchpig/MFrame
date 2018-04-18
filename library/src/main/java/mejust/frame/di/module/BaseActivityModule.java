package mejust.frame.di.module;

import dagger.Module;
import dagger.Provides;
import mejust.frame.di.scope.ActivityScope;
import mejust.frame.mvp.BaseContract;

/**
 * 创建时间:2018-02-01 10:40<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2018-02-01 10:40<br/>
 * 描述:
 */
@Module
public abstract class BaseActivityModule<V extends BaseContract.View> {

    private final V mView;

    public BaseActivityModule(V v) {
        mView = v;
    }

    @ActivityScope
    @Provides
    public V provideView() {
        return mView;
    }
}
