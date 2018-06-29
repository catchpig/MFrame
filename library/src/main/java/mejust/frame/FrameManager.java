package mejust.frame;

import android.app.Application;
import android.support.annotation.NonNull;
import conm.zhuazhu.common.utils.Utils;
import mejust.frame.common.image.IImageLoadManager;
import mejust.frame.common.image.ImageConfig;
import mejust.frame.common.json.IJsonManager;
import mejust.frame.common.log.DebugLogTree;
import mejust.frame.common.log.ReleaseLogTree;
import mejust.frame.data.FrameConfig;
import mejust.frame.di.component.DaggerFrameComponent;
import mejust.frame.di.component.FrameComponent;
import mejust.frame.mvp.view.BarLifecycleCallbacksImpl;
import mejust.frame.net.NetManager;
import mejust.frame.net.config.NetConfig;
import mejust.frame.widget.ToastFrame;
import timber.log.Timber;

/**
 * @author wangpeifeng
 * @date 2018/05/31 10:26
 */
public class FrameManager {

    private static IImageLoadManager imageLoadManager;

    private static NetManager netManager;

    private static IJsonManager iJsonManager;

    private static FrameConfig frameConfig;

    public static FrameComponent init(Application application, ImageConfig imageConfig,
            NetConfig netConfig, FrameConfig frameConfig) {
        FrameComponent frameComponent = DaggerFrameComponent.builder()
                .application(application)
                .imageConfig(imageConfig)
                .netConfig(netConfig)
                .frameConfig(frameConfig)
                .build();
        imageLoadManager = frameComponent.imageLoadManager();
        netManager = frameComponent.netManager();
        iJsonManager = frameComponent.jsonManager();
        FrameManager.frameConfig = frameConfig;
        if (frameConfig.isDebug()) {
            Timber.plant(new DebugLogTree());
        } else {
            Timber.plant(new ReleaseLogTree());
        }
        Utils.init(application);
        application.registerActivityLifecycleCallbacks(new BarLifecycleCallbacksImpl());
        ToastFrame.init(application);
        return frameComponent;
    }

    public static void setImageLoadManager(@NonNull IImageLoadManager loadManager) {
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

    public static void setJsonManager(@NonNull IJsonManager jsonManager) {
        FrameManager.iJsonManager = jsonManager;
    }

    public static IJsonManager jsonManager() {
        if (iJsonManager == null) {
            throw new IllegalArgumentException("please init() or setJsonManager() first");
        }
        return iJsonManager;
    }

    public static FrameConfig provideFrameConfig() {
        return frameConfig;
    }
}
