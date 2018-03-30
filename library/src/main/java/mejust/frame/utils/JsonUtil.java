package mejust.frame.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

    private static final Gson GSON =
            new GsonBuilder().setDateFormat(AppConfig.DATE_FORMAT).create();

    public static Gson getGson() {
        return GSON;
    }

    /**
     * 转换为字符串
     */
    public static String toString(Object object) {
        return GSON.toJson(object);
    }

    /**
     * 字符串转化为对象
     */
    public static <T> T fromJson(String jsonString, Class<T> classOfT) {
        return GSON.fromJson(jsonString, classOfT);
    }

    /**
     * 字符串转换为List<T>
     *
     * @param <T> 泛型
     */
    public static <T> List<T> toList(String jsonString, Class<T> cls) {
        Type type = new ListParameterizedType(cls);
        return GSON.fromJson(jsonString, type);
    }

    /**
     * 字符串转换为List<T> ， 此方法可解决转换时的类型擦除
     *
     * @param <T> 泛型
     */
    @Deprecated
    public static <T> List<T> fromJsonList(String json, Class<T> cls) {
        List<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(GSON.fromJson(elem, cls));
        }
        return mList;
    }

    private static class ListParameterizedType implements ParameterizedType {
        private Type type;

        private ListParameterizedType(Type type) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[] { type };
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
        // implement equals method too! (as per javadoc)
    }
}
