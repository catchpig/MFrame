package mejust.frame.annotation.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import mejust.frame.annotation.LayoutId;
import mejust.frame.utils.log.Logger;

/**
 * @author litao
 * @date 2018/04/18 9:20
 */
public class AnnotationBind {

    private static final String TAG = AnnotationBind.class.getSimpleName();

    /**
     * 注入activity的布局文件
     */
    public static void injectLayoutId(@NonNull Activity activity) {
        LayoutId layoutId = AnnotionUtils.annotationRecycle(activity.getClass(), LayoutId.class);
        if (layoutId != null) {
            activity.setContentView(layoutId.value());
        } else {
            Logger.i(TAG, "layoutId为空,请设置layoutId");
        }
    }

    /**
     * 注入fragment的布局文件
     *
     * @return 当前注解文件的View
     */
    public static View injectLayoutId(Fragment fragment, LayoutInflater inflater,
            ViewGroup container) {
        LayoutId layoutId = AnnotionUtils.annotationRecycle(fragment.getClass(), LayoutId.class);
        View v = null;
        if (layoutId != null) {
            v = inflater.inflate(layoutId.value(), container, false);
        } else {
            Logger.i(TAG, "layoutId为空,请设置layoutId");
        }
        return v;
    }
}
