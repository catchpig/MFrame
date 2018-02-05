package mejust.frame.bind;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import mejust.frame.annotation.ImageRightFirstEvent;
import mejust.frame.annotation.ImageRightSecondEvent;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.TextRightFirstEvent;
import mejust.frame.annotation.TextRightSecondEvent;
import mejust.frame.annotation.utils.AnnotionUtils;
import mejust.frame.annotation.utils.TitleBarAnnotationUtils;
import mejust.frame.utils.log.Logger;
import mejust.frame.widget.title.TitleBar;

/**
 * 创建时间:2017/4/2 18:11
 * 创建人: 李涛
 * 修改人:
 * 修改时间:
 * 描述:注解注入工具
 */

public class AnnotationBind {
    private static final String TAG = "AnnotationBind";

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
     * 绑定注解(@TitleBar,@TextRightFirstEvent,@TextRightSecondEvent,@ImageRightFirstEvent,@ImageRightSecondEvent)
     *
     * @return true:有TitleBar这个注解<br/>false:没有TitleBar这个注解
     */
    public static void injectTitleBar(@NonNull Activity activity, @NonNull TitleBar titleBar) {
        Class<?> activityClass = activity.getClass();
        if (!TitleBarAnnotationUtils.setTitleBarAnnotation(activityClass, titleBar)) {
            return;
        }
        Method[] methods = activityClass.getDeclaredMethods();
        for (Method method : methods) {
            //如果当前方法是静态方法或者私有方法,直接忽略
            if (Modifier.isStatic(method.getModifiers()) || Modifier.isPrivate(
                    method.getModifiers())) {
                continue;
            }
            //第一个文字的注解
            TextRightFirstEvent textRightFirstEvent =
                    method.getAnnotation(TextRightFirstEvent.class);
            TitleBarAnnotationUtils.setRightTextFirstAnnotation(activity, method,
                    textRightFirstEvent, titleBar);
            //第二个文字的注解
            TextRightSecondEvent textRightSecondEvent =
                    method.getAnnotation(TextRightSecondEvent.class);
            TitleBarAnnotationUtils.setRightTextSecondAnnotation(activity, method,
                    textRightSecondEvent, titleBar);
            //第一个图片的注释
            ImageRightFirstEvent imageRightFirstEvent =
                    method.getAnnotation(ImageRightFirstEvent.class);
            TitleBarAnnotationUtils.setRightFirstImageAnnotation(activity, method,
                    imageRightFirstEvent, titleBar);
            //第二个图片的注释
            ImageRightSecondEvent imageRightSecondEvent =
                    method.getAnnotation(ImageRightSecondEvent.class);
            TitleBarAnnotationUtils.setRightSecondImageAnnotation(activity, method,
                    imageRightSecondEvent, titleBar);
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
