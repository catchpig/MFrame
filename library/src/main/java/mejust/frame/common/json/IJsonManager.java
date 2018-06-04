package mejust.frame.common.json;

import java.util.List;

/**
 * @author wangpeifeng
 * @date 2018/06/04 13:58
 */
public interface IJsonManager {

    String toString(Object object);

    <T> T fromJson(String jsonString, Class<T> classOfT);

    <T> List<T> fromJsonList(String jsonString, Class<T> cls);
}
