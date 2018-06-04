package mejust.frame.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import mejust.frame.net.config.NetConfig;

/**
 * @author wangpeifeng
 * @date 2018/06/04 14:01
 */
public class GsonManagerImpl implements IJsonManager {

    private Gson gson;

    public GsonManagerImpl(NetConfig netConfig) {
        this.gson = new GsonBuilder().setDateFormat(netConfig.getDateFormat()).create();
    }

    @Override
    public String toString(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T fromJson(String jsonString, Class<T> classOfT) {
        return gson.fromJson(jsonString, classOfT);
    }

    @Override
    public <T> List<T> fromJsonList(String jsonString, Class<T> cls) {
        Type type = new GsonManagerImpl.ListParameterizedType(cls);
        return gson.fromJson(jsonString, type);
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
