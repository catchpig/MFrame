package mejust.frame.refactor;

import android.app.Application;
import mejust.frame.refactor.config.FrameConfig;
import mejust.frame.refactor.di.DaggerFrameComponent;
import mejust.frame.refactor.di.FrameComponent;
import mejust.frame.refactor.image.IImageLoadManager;
import mejust.frame.refactor.image.ImageConfig;
import mejust.frame.refactor.net.NetConfig;
import mejust.frame.refactor.net.NetManager;

/**
 * @author wangpeifeng
 * @date 2018/05/31 10:26
 */
public class FrameManager {

    private static IImageLoadManager imageLoadManager;

    private static NetManager netManager;

    public static void init(Application application, ImageConfig imageConfig, NetConfig netConfig,
            FrameConfig frameConfig) {
        FrameComponent frameComponent = DaggerFrameComponent.builder()
                .application(application)
                .imageConfig(imageConfig)
                .netConfig(netConfig)
                .frameConfig(frameConfig)
                .build();
        imageLoadManager = frameComponent.imageLoadManager();
        netManager = frameComponent.netManager();
    }

    public static void setImageLoadManager(IImageLoadManager loadManager) {
        FrameManager.imageLoadManager = loadManager;
    }

    public static IImageLoadManager imageLoadManager() {
        if (imageLoadManager == null) {
            throw new IllegalArgumentException("please init() or setImageLoadManager() first");
        }
        return imageLoadManager;
    }

    public static NetManager netManager() {
        if (netManager == null) {
            throw new IllegalArgumentException("please call init() first");
        }
        return netManager;
    }
}
