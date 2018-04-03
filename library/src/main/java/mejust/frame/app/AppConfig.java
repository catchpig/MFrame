package mejust.frame.app;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:16
 * <p>
 * 系统固定字段定义，上层应用可继承，添加字段
 */
public interface AppConfig {

    /**
     * 程序运行状态区分
     */
    boolean DEBUG = false;

    /**
     * 网络请求详情打印
     */
    String URL_LOG = "OkHttp";

    /**
     * 连接超时，单位：秒
     */
    long CONNECT_TIME_OUT_DEFAULT = 10;

    /**
     * 读取超时，单位：秒
     */
    long READ_TIME_OUT_DEFAULT = 10;

    /**
     * 写入超时，单位：秒
     */
    long WRITE_TIME_OUT_DEFAULT = 10;

    /**
     * json解析时间格式
     */
    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 网络状态监听标志判断
     */
    boolean NETWORK_STATUS_MONITORING = true;
}
