package mejust.frame.image;

import android.widget.ImageView;
import java.io.File;

/**
 * @author wangpeifeng
 * @date 2018/05/29 15:10
 */
public interface IImageLoad {

    void init(ImageAppConfig imageAppConfig);

    void loadNet(ImageView imageView, String url);

    void loadNet(ImageView imageView, String url, ImageLoadConfig loadConfig);

    void loadLocal(ImageView imageView, File file);

    void loadLocal(ImageView imageView, File file, ImageLoadConfig loadConfig);

    void loadAsset(ImageView imageView, String assetName);

    void loadAsset(ImageView imageView, String assetName, ImageLoadConfig loadConfig);
}
