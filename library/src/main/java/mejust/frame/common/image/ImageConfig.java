package mejust.frame.common.image;

/**
 * @author wangpeifeng
 * @date 2018/05/29 15:05
 */
public class ImageConfig {

    /**
     * 配置的图片域名
     */
    private String hostUrl;

    /**
     * 加载中占位图
     */
    private int placeholderResId;
    /**
     * 加载失败图片
     */
    private int errorResId;

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public int getPlaceholderResId() {
        return placeholderResId;
    }

    public void setPlaceholderResId(int placeholderResId) {
        this.placeholderResId = placeholderResId;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public void setErrorResId(int errorResId) {
        this.errorResId = errorResId;
    }
}
