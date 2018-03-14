package mejust.frame.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import mejust.frame.app.AppConfig;

/**
 * @author : Beaven
 * @date : 2017-12-20 10:53
 * <p>
 * Json解析工具类，使用Gson实现
 */

public class JsonUtil {

    private JsonUtil() {
        throw new RuntimeException("this class not be instantiated");
    }

    private static final Gson gson =
            new GsonBuilder().setDateFormat(AppConfig.DATE_FORMAT).create();

    public static Gson getGson() {
        return gson;
    }

    /**
     * 转换为字符串
     * @param object
     * @return
     */
    public static String toString(Object object) {
        return gson.toJson(object);
    }

    /**
     * 字符串转化为对象
     * @param jsonString
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonString, Class<T> classOfT) {
        return gson.fromJson(jsonString, classOfT);
    }

    /**
     * 字符串转换为List<T>
     * @param jsonString
     * @param <T> 泛型
     * @return
     */
    public static <T> List<T> fromJson(String jsonString){
        return gson.fromJson(jsonString,new TypeToken<List<T>>(){}.getType());
    }
}
