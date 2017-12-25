package mejust.frame.bind;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import mejust.frame.annotation.ImageRightFirstEvent;
import mejust.frame.annotation.ImageRightSecondEvent;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.StatusBar;
import mejust.frame.annotation.TextRightFirstEvent;
import mejust.frame.annotation.TextRightSecondEvent;
import mejust.frame.annotation.utils.AnnotionUtils;
import mejust.frame.annotation.utils.TitleBarAnnotationUtils;
import mejust.frame.utils.log.Logger;
import mejust.frame.widget.title.TitleBar;
import qiu.niorgai.StatusBarCompat;

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
     * @param activity
     */
    public static void injectLayoutId(@NonNull Activity activity){
        LayoutId layoutId = AnnotionUtils.annotationRecycle(activity.getClass(),LayoutId.class);
        if (layoutId != null) {
            activity.setContentView(layoutId.value());
        } else {
            Logger.i(TAG, "layoutId为空,请设置layoutId");
        }
    }

    /**
     * 注入StatusBar注解
     * @param activity
     */
    public static void injectStatusBar(@NonNull Activity activity){
        StatusBar statusBar = AnnotionUtils.annotation(activity.getClass(),StatusBar.class);
        if(statusBar!=null){
            int color = statusBar.value();
            int alpha = statusBar.alpha();
            boolean translucent = statusBar.translucent();
            boolean hiddenBackground = statusBar.hiddenBackground();
            if(translucent){
                StatusBarCompat.translucentStatusBar(activity,hiddenBackground);
            }else{
                if(color!=-1){
                    StatusBarCompat.setStatusBarColor(activity, ContextCompat.getColor(activity,
                            color),alpha);
                }
            }
        }
    }
    /**
     * 绑定注解(@TitleBar,@TextRightFirstEvent,@TextRightSecondEvent,@ImageRightFirstEvent,@ImageRightSecondEvent)
     * @param activity
     * @param titleBar
     * @return true:有TitleBar这个注解<br/>false:没有TitleBar这个注解
     */
    public static void injectTitleBar(@NonNull Activity activity, @NonNull TitleBar titleBar) {
        Class<?> activityClass = activity.getClass();
        if(!TitleBarAnnotationUtils.setTitleBarAnnotation(activityClass, titleBar)){
            return;
        }
        Method[] methods = activityClass.getDeclaredMethods();
        for (Method method : methods) {
            //如果当前方法是静态方法或者私有方法,直接忽略
            if (Modifier.isStatic(method.getModifiers()) || Modifier.isPrivate(method
                    .getModifiers())) {
                continue;
            }
            //第一个文字的注解
            TextRightFirstEvent textRightFirstEvent = method.getAnnotation(TextRightFirstEvent
                    .class);
            TitleBarAnnotationUtils.setRightTextFirstAnnotation(activity, method,
                    textRightFirstEvent, titleBar);
            //第二个文字的注解
            TextRightSecondEvent textRightSecondEvent = method.getAnnotation(TextRightSecondEvent
                    .class);
            TitleBarAnnotationUtils.setRightTextSecondAnnotation(activity, method,
                    textRightSecondEvent, titleBar);
            //第一个图片的注释
            ImageRightFirstEvent imageRightFirstEvent = method.getAnnotation(ImageRightFirstEvent
                    .class);
            TitleBarAnnotationUtils.setRightFirstImageAnnotation(activity, method, imageRightFirstEvent, titleBar);
            //第二个图片的注释
            ImageRightSecondEvent imageRightSecondEvent = method.getAnnotation(ImageRightSecondEvent
                    .class);
            TitleBarAnnotationUtils.setRightSecondImageAnnotation(activity, method, imageRightSecondEvent, titleBar);
        }
    }

    /**
     * 注入fragment的布局文件
     * @param fragment
     * @param inflater
     * @param container
     * @return 当前注解文件的View
     */
    public static View injectLayoutId(Fragment fragment, LayoutInflater inflater,
                                      ViewGroup container){
        LayoutId layoutId = AnnotionUtils.annotationRecycle(fragment.getClass(),LayoutId.class);
        View v = null;
        if(layoutId!=null){
            v = inflater.inflate(layoutId.value(),container,false);
        }else{
            Logger.i(TAG,"layoutId为空,请设置layoutId");
        }
        return v;
    }

}
