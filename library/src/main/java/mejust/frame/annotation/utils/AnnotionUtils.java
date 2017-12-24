package mejust.frame.annotation.utils;

import java.lang.annotation.Annotation;

/**
 * 创建时间:2017/4/2 18:18
 * 创建人: 李涛
 * 修改人:
 * 修改时间:
 * 描述:获取注解内容工具类
 */

public class AnnotionUtils {
    /**
     * 获取注解类
     * @param cls 使用了注解的class
     * @param aCls 注解的class
     * @param <A>
     * @return
     */
    public static <A extends Annotation> A annotation(Class<?> cls, Class<A> aCls){
        if (cls == null) {
            return null;
        }
        A a = null;
        if(cls.isAnnotationPresent(aCls)){
            a = cls.getAnnotation(aCls);
        }
        if (a == null) {
            return annotation(cls.getSuperclass(),aCls);
        }
        return a;
    }
}
