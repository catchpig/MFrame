package mejust.frame.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    private static final Gson gson = new GsonBuilder().create();

    public static Gson getGson() {
        return gson;
    }

    public static String toString(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String jsonString, Class<T> classOfT) {
        return gson.fromJson(jsonString, classOfT);
    }
}
