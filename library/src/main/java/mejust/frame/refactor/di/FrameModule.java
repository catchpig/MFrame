package mejust.frame.refactor.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import mejust.frame.refactor.image.GlideLoadManager;
import mejust.frame.refactor.image.IImageLoadManager;
import mejust.frame.refactor.image.ImageConfig;
import mejust.frame.refactor.net.NetConfig;
import mejust.frame.refactor.net.NetManager;

/**
 * @author wangpeifeng
 * @date 2018/05/31 10:32
 */
@Module
public class FrameModule {

    @Singleton
    @Provides
    public IImageLoadManager provideImageLoadManager(ImageConfig imageConfig) {
        return new GlideLoadManager(imageConfig);
    }

    @Singleton
    @Provides
    public NetManager provideNetManager(NetConfig netConfig) {
        return new NetManager(netConfig);
    }
}
