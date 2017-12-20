package mejust.frame.app;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:16
 * <p>
 * 系统固定字段定义，上层应用可继承，添加字段
 */

public class AppConfig {

    /**
     * 程序运行状态区分
     */
    public static boolean DEBUG = false;

    /**
     * api base url 字段
     */
    public static final String BASE_URL = "baseUrl";

    /**
     * 网络请求详情打印
     */
    public static final String URL_LOG = "OkHttp";
    /**
     * 连接超时，单位：秒
     */
    public static final long CONNECT_TIME_OUT_DEFAULT = 10;
    /**
     * 读取超时，单位：秒
     */
    public static final long READ_TIME_OUT_DEFAULT = 10;
    /**
     * 写入超时，单位：秒
     */
    public static final long WRITE_TIME_OUT_DEFAULT = 10;
}
