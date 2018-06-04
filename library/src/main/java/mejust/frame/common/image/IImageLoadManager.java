package mejust.frame.common.image;

import android.widget.ImageView;
import java.io.File;

/**
 * @author wangpeifeng
 * @date 2018/05/29 15:10
 */
public interface IImageLoadManager {

    /**
     * 加载网络图片
     *
     * @param imageView view视图
     * @param url 地址
     */
    void loadNet(ImageView imageView, String url);

    /**
     * 加载网络图片
     *
     * @param imageView view视图
     * @param url 地址
     * @param loadConfig 一次加载的图片配置
     */
    void loadNet(ImageView imageView, String url, ImageLoadConfig loadConfig);

    /**
     * 加载网络图片
     *
     * @param imageView view视图
     * @param file 文件路径
     */
    void loadLocal(ImageView imageView, File file);

    /**
     * 加载网络图片
     *
     * @param imageView view视图
     * @param file 文件路径
     * @param loadConfig 一次加载的图片配置
     */
    void loadLocal(ImageView imageView, File file, ImageLoadConfig loadConfig);

    /**
     * 加载网络图片
     *
     * @param imageView view视图
     * @param assetName asset文件夹中的图片
     */
    void loadAsset(ImageView imageView, String assetName);

    /**
     * 加载网络图片
     *
     * @param imageView view视图
     * @param assetName asset文件夹中的图片
     * @param loadConfig 一次加载的图片配置
     */
    void loadAsset(ImageView imageView, String assetName, ImageLoadConfig loadConfig);
}
