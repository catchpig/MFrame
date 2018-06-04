package mejust.frame.refactor;

import android.app.Application;
import conm.zhuazhu.common.utils.Utils;
import mejust.frame.app.CrashHandler;
import mejust.frame.refactor.config.FrameConfig;
import mejust.frame.refactor.di.DaggerFrameComponent;
import mejust.frame.refactor.di.FrameComponent;
import mejust.frame.refactor.image.IImageLoadManager;
import mejust.frame.refactor.image.ImageConfig;
import mejust.frame.refactor.net.NetManager;
import mejust.frame.refactor.net.config.NetConfig;
import mejust.frame.utils.log.DebugLogTree;
import mejust.frame.utils.log.ReleaseLogTree;
import mejust.frame.widget.ToastFrame;
import timber.log.Timber;

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
        if (frameConfig.isDebug()) {
            Timber.plant(new DebugLogTree());
        } else {
            Timber.plant(new ReleaseLogTree());
        }
        Utils.init(application);
        ToastFrame.init(application);
        CrashHandler.getInstance().init(application);
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
