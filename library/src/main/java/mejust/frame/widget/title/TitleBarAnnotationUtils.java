package mejust.frame.widget.title;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.zhuazhu.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import mejust.frame.annotation.ImageRightFirstEvent;
import mejust.frame.annotation.ImageRightSecondEvent;
import mejust.frame.annotation.TextRightFirstEvent;
import mejust.frame.annotation.TextRightSecondEvent;
import mejust.frame.annotation.TitileBar;
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

    public static void inject(@NonNull Activity activity, @NonNull TitleBar titleBar) {
        Class<?> activityClass = activity.getClass();
        setTitleBarAnnotation(activityClass, titleBar);
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
            setRightTextFirstAnnotation(activity, method, textRightFirstEvent, titleBar);
            //第二个文字的注解
            TextRightSecondEvent textRightSecondEvent = method.getAnnotation(TextRightSecondEvent
                    .class);
            setRightTextSecondAnnotation(activity, method, textRightSecondEvent, titleBar);
            //第一个图片的注释
            ImageRightFirstEvent imageRightFirstEvent = method.getAnnotation(ImageRightFirstEvent
                    .class);
            setRightFirstImageAnnotation(activity, method, imageRightFirstEvent, titleBar);
            //第二个图片的注释
            ImageRightSecondEvent imageRightSecondEvent = method.getAnnotation(ImageRightSecondEvent
                    .class);
            setRightSecondImageAnnotation(activity, method, imageRightSecondEvent, titleBar);
        }
    }

    public static void setRightSecondImageAnnotation(@NonNull Object object, final Method method,
                                                     ImageRightSecondEvent imageRightSecondEvent,
                                                     TitleBar titleBar) {
        if (imageRightSecondEvent == null) {
            return;
        } else {
            int resId = imageRightSecondEvent.value();
            titleBar.setRightImage1(resId);
            titleBar.setImageRightSecondListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        method.invoke(object, v);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void setRightFirstImageAnnotation(@NonNull Object object, final Method method,
                                                    ImageRightFirstEvent imageRightFirstEvent,
                                                    TitleBar titleBar) {
        if (imageRightFirstEvent == null) {
            return;
        } else {
            int resId = imageRightFirstEvent.value();
            titleBar.setRightImage1(resId);
            titleBar.setImageRightFirstListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        method.invoke(object, v);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void setRightTextSecondAnnotation(@NonNull Object object, Method method,
                                                    TextRightSecondEvent textRightSecondEvent,
                                                    TitleBar titleBar) {
        if (textRightSecondEvent == null) {
            return;
        } else {
            String text = textRightSecondEvent.value();
            titleBar.setRightText2(text);
            titleBar.setTextRightSecondListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        method.invoke(object, v);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void setRightTextFirstAnnotation(@NonNull Object object, Method method,
                                                   TextRightFirstEvent textRightFirstEvent,
                                                   TitleBar titleBar) {
        if (textRightFirstEvent == null) {
            return;
        } else {
            String text = textRightFirstEvent.value();
            titleBar.setRightText1(text);
            titleBar.setTextRightFirstListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        method.invoke(object, v);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 设置TitleBar
     *
     * @param cls
     * @param titleBar
     */
    private static void setTitleBarAnnotation(Class<?> cls, TitleBar titleBar) {
        TitileBar bar = annotation(cls, TitileBar.class);
        if (bar == null) {
            Logger.e(TAG, cls.getName() + "当前类没有TitleBar注解");
        } else {
            titleBar.setTitleText(bar.value());
            boolean hiddenBack = bar.hiddenBack();
            if (hiddenBack) {
                titleBar.hiddenBackButton();
            }
            int textcolor = bar.textColor();
            if(textcolor != -1){
                titleBar.setTextColor(textcolor);
            }
            String backtext = bar.backText();
            if(StringUtils.isNotEmpty(backtext)){
                titleBar.setBackText(backtext);
            }
            int backgroundColor = bar.background();
            if(backgroundColor != -1){
                titleBar.setTitleBarBackgroundColor(backgroundColor);
            }
        }
    }

    private static <T extends Annotation> T annotation(Class<?> cls, Class<T> aCls) {
        if (cls.isAnnotationPresent(aCls)) {
            return cls.getAnnotation(aCls);
        } else {
            return null;
        }
    }
}
