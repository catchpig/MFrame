package mejust.frame.refactor.di;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;
import mejust.frame.refactor.config.FrameConfig;
import mejust.frame.refactor.image.IImageLoadManager;
import mejust.frame.refactor.image.ImageConfig;
import mejust.frame.refactor.net.NetConfig;
import mejust.frame.refactor.net.NetManager;

/**
 * @author wangpeifeng
 * @date 2018/05/31 10:55
 */
@Singleton
@Component(modules = { FrameModule.class })
public interface FrameComponent {

    Application application();

    ImageConfig imageConfig();

    NetConfig netConfig();

    FrameConfig frameConfig();

    IImageLoadManager imageLoadManager();

    NetManager netManager();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder imageConfig(ImageConfig imageConfig);

        @BindsInstance
        Builder netConfig(NetConfig netConfig);

        @BindsInstance
        Builder frameConfig(FrameConfig frameConfig);

        FrameComponent build();
    }
}
