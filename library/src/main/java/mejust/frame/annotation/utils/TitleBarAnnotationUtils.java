package mejust.frame.annotation.utils;

import android.support.annotation.NonNull;
import java.lang.reflect.Method;
import mejust.frame.annotation.ImageRightFirstEvent;
import mejust.frame.annotation.ImageRightSecondEvent;
import mejust.frame.annotation.TextRightFirstEvent;
import mejust.frame.annotation.TextRightSecondEvent;
import mejust.frame.annotation.TitleBar;
import mejust.frame.utils.log.Logger;

/**
 * 创建时间:2017/12/23 21:29<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/23 21:29<br/>
 * 描述:TitleBar注解注解的注入工具类
 */

public class TitleBarAnnotationUtils {
    private static final String TAG = "TitleBarAnnotationUtils";

    public static void setRightSecondImageAnnotation(@NonNull Object object, final Method method,
                                                     ImageRightSecondEvent imageRightSecondEvent,
                                                     mejust.frame.widget.title.TitleBar titleBar) {
        if (imageRightSecondEvent == null) {
            return;
        } else {
            int resId = imageRightSecondEvent.value();
            //titleBar.setRightImage1(resId);
            //titleBar.setImageRightSecondListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        try {
            //            method.invoke(object, v);
            //        } catch (IllegalAccessException e) {
            //            e.printStackTrace();
            //        } catch (InvocationTargetException e) {
            //            e.printStackTrace();
            //        }
            //    }
            //});
        }
    }

    public static void setRightFirstImageAnnotation(@NonNull Object object, final Method method,
                                                    ImageRightFirstEvent imageRightFirstEvent,
                                                    mejust.frame.widget.title.TitleBar titleBar) {
        if (imageRightFirstEvent == null) {
            return;
        } else {
            int resId = imageRightFirstEvent.value();
            //titleBar.setRightImage1(resId);
            //titleBar.setImageRightFirstListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        try {
            //            method.invoke(object, v);
            //        } catch (IllegalAccessException e) {
            //            e.printStackTrace();
            //        } catch (InvocationTargetException e) {
            //            e.printStackTrace();
            //        }
            //    }
            //});
        }
    }

    public static void setRightTextSecondAnnotation(@NonNull Object object, Method method,
                                                    TextRightSecondEvent textRightSecondEvent,
                                                    mejust.frame.widget.title.TitleBar titleBar) {
        if (textRightSecondEvent == null) {
            return;
        } else {
            String text = textRightSecondEvent.value();
            //titleBar.setRightText2(text);
            //titleBar.setTextRightSecondListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        try {
            //            method.invoke(object, v);
            //        } catch (IllegalAccessException e) {
            //            e.printStackTrace();
            //        } catch (InvocationTargetException e) {
            //            e.printStackTrace();
            //        }
            //    }
            //});
        }
    }

    public static void setRightTextFirstAnnotation(@NonNull Object object, Method method,
                                                   TextRightFirstEvent textRightFirstEvent,
                                                   mejust.frame.widget.title.TitleBar titleBar) {
        if (textRightFirstEvent == null) {
            return;
        } else {
            String text = textRightFirstEvent.value();
            //titleBar.setRightText1(text);
            //titleBar.setTextRightFirstListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        try {
            //            method.invoke(object, v);
            //        } catch (IllegalAccessException e) {
            //            e.printStackTrace();
            //        } catch (InvocationTargetException e) {
            //            e.printStackTrace();
            //        }
            //    }
            //});
        }
    }

    /**
     * 设置TitleBar
     *
     * @param cls
     * @param titleBar
     * @return true:有TitleBar这个注解<br/>false:没有TitleBar这个注解
     */
    public static boolean setTitleBarAnnotation(Class<?> cls, mejust.frame.widget.title.TitleBar titleBar) {
        TitleBar bar = AnnotionUtils.annotation(cls, TitleBar.class);
        if (bar == null) {
            Logger.e(TAG, cls.getName() + "当前类没有TitleBar注解");
            return false;
        } else {
            //titleBar.setTitleText(bar.value());
            //boolean hiddenBack = bar.hiddenBack();
            //if (hiddenBack) {
            //    titleBar.hiddenBackButton();
            //}
            //int textcolor = bar.textColor();
            //if(textcolor != -1){
            //    titleBar.setTextColor(textcolor);
            //}
            //String backtext = bar.backText();
            //if(StringUtils.isNotEmpty(backtext)){
            //    titleBar.setBackText(backtext);
            //}
            //int backgroundColor = bar.background();
            //if(backgroundColor != -1){
            //    titleBar.setTitleBarBackgroundColor(backgroundColor);
            //}
            return true;
        }
    }

    /**
     * 判断当前类是否有TitileBar这个注解
     * @param cls
     * @return
     */
    public static boolean isTitleBarAnnotation(Class<?> cls){
        TitleBar titileBar = AnnotionUtils.annotation(cls,TitleBar.class);
        if(titileBar==null){
            return false;
        }
        return true;
    }
}
