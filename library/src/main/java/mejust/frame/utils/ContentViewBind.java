package mejust.frame.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import mejust.frame.annotation.LayoutId;

/**
 * @author litao
 * @date 2018/04/18 9:20
 */
public class ContentViewBind {

    /**
     * 注入activity的布局文件
     */
    public static void injectLayoutId(@NonNull Activity activity) {
        LayoutId layoutId = AnnotationUtils.annotationRecycle(activity.getClass(), LayoutId.class);
        if (layoutId != null) {
            activity.setContentView(layoutId.value());
        } else {
            throw new IllegalArgumentException(
                    "layoutId is null, please use @LayoutId in Activity");
        }
    }

    /**
     * 注入fragment的布局文件
     *
     * @return 当前注解文件的View
     */
    public static View injectLayoutId(Fragment fragment, LayoutInflater inflater,
            ViewGroup container) {
        LayoutId layoutId = AnnotationUtils.annotationRecycle(fragment.getClass(), LayoutId.class);
        View v;
        if (layoutId != null) {
            v = inflater.inflate(layoutId.value(), container, false);
        } else {
            throw new IllegalArgumentException(
                    "layoutId is null, please use @LayoutId in Fragment");
        }
        return v;
    }
}
