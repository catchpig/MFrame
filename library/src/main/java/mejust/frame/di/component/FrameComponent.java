package mejust.frame.di.component;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;
import mejust.frame.common.json.IJsonManager;
import mejust.frame.config.FrameConfig;
import mejust.frame.di.module.FrameModule;
import mejust.frame.image.IImageLoadManager;
import mejust.frame.image.ImageConfig;
import mejust.frame.net.NetManager;
import mejust.frame.net.config.NetConfig;

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

    IJsonManager jsonManager();

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
