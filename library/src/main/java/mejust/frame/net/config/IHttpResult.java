package mejust.frame.net.config;

/**
 * @author wangpeifeng
 * @date 2018/05/31 18:01
 */
public interface IHttpResult<T> {

    T getResultData();

    String getResultCode();

    String getResultMessage();
}
