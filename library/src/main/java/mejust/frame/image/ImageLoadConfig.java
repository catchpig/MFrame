package mejust.frame.image;

/**
 * @author wangpeifeng
 * @date 2018/05/29 15:08
 */
public class ImageLoadConfig extends ImageConfig {

    /**
     * 显示圆形图片
     */
    private boolean isShowCircle;

    /**
     * 显示圆角图片圆角
     */
    private int roundRadius;

    public ImageLoadConfig() {
    }

    public ImageLoadConfig(boolean isShowCircle) {
        this.isShowCircle = isShowCircle;
    }

    public ImageLoadConfig(int roundRadius) {
        this.roundRadius = roundRadius;
    }

    public boolean isShowCircle() {
        return isShowCircle;
    }

    public int getRoundRadius() {
        return roundRadius;
    }
}
