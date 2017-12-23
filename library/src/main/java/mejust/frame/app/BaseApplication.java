package mejust.frame.app;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

import mejust.frame.cash.CrashHandler;
import mejust.frame.utils.log.DebugLogTree;
import mejust.frame.utils.log.ReleaseLogTree;
import timber.log.Timber;

/**
 * @author : Beaven
 * @date : 2017-12-19 21:42
 */

public abstract class BaseApplication extends com.zhuazhu.application.BaseApplication {
    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                WaveSwipeHeader header = new WaveSwipeHeader(context);
                layout.setEnableHeaderTranslationContent(false);
                layout.setPrimaryColors(Color.parseColor("#333333"),Color.parseColor("#7898ab"));
                return header;
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new BallPulseFooter(context);
            }
        });
    }
    private static BaseApplication application;

    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initCash();
        initLog();
    }

    public static BaseApplication getApplication() {
        return application;
    }

    /**
     * 初始化异常捕获抓取
     */
    public void initCash() {
        CrashHandler.getInstance().init(getApplicationContext());
    }

    /**
     * 初始化日志打印
     */
    private void initLog() {
        if (AppConfig.DEBUG) {
            Timber.plant(new DebugLogTree());
        } else {
            Timber.plant(new ReleaseLogTree());
        }
    }
}
