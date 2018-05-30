package mejust.frame.image;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import conm.zhuazhu.common.utils.StringUtils;
import java.io.File;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author wangpeifeng
 * @date 2018/05/29 15:26
 */
public class GlideLoadManager implements IImageLoadManager {

    private static final String ASSET_FILE_HOST = "file:///android_asset/";

    private ImageAppConfig imageAppConfig;

    @Override
    public void init(ImageAppConfig imageAppConfig) {
        this.imageAppConfig = imageAppConfig;
    }

    @Override
    public void loadNet(ImageView imageView, String url) {
        loadNet(imageView, url, null);
    }

    @Override
    public void loadNet(ImageView imageView, String url, ImageLoadConfig loadConfig) {
        String realUrl;
        if (StringUtils.validateHttpOrHttps(url)) {
            realUrl = url;
        } else {
            if (imageAppConfig == null) {
                throw new IllegalArgumentException("image load config not init");
            } else {
                realUrl = imageAppConfig.getHostUrl() + url;
            }
        }
        requestImage(Glide.with(imageView).load(realUrl), loadConfig, imageView);
    }

    @Override
    public void loadLocal(ImageView imageView, File file) {
        loadLocal(imageView, file, null);
    }

    @Override
    public void loadLocal(ImageView imageView, File file, ImageLoadConfig loadConfig) {
        requestImage(Glide.with(imageView).load(file), loadConfig, imageView);
    }

    @Override
    public void loadAsset(ImageView imageView, String assetName) {
        loadAsset(imageView, assetName, null);
    }

    @Override
    public void loadAsset(ImageView imageView, String assetName, ImageLoadConfig loadConfig) {
        requestImage(Glide.with(imageView).load(ASSET_FILE_HOST + assetName), loadConfig,
                imageView);
    }

    private void requestImage(RequestBuilder requestBuilder, ImageLoadConfig loadConfig,
            ImageView imageView) {
        if (loadConfig == null) {
            if (imageAppConfig == null) {
                requestBuilder.into(imageView);
            } else {
                RequestOptions requestOptions = new RequestOptions();
                if (imageAppConfig.getPlaceholderResId() > 0) {
                    requestOptions =
                            requestOptions.placeholder(imageAppConfig.getPlaceholderResId());
                }
                if (imageAppConfig.getErrorResId() > 0) {
                    requestOptions = requestOptions.error(imageAppConfig.getErrorResId());
                }
                requestBuilder.apply(requestOptions).into(imageView);
            }
        } else {
            RequestOptions requestOptions = new RequestOptions();
            if (loadConfig.getPlaceholderResId() > 0) {
                requestOptions = requestOptions.placeholder(imageAppConfig.getPlaceholderResId());
            }
            if (loadConfig.getErrorResId() > 0) {
                requestOptions = requestOptions.error(imageAppConfig.getErrorResId());
            }
            if (loadConfig.isShowCircle()) {
                requestOptions = requestOptions.circleCrop();
            }
            if (loadConfig.getRoundRadius() > 0) {
                requestOptions = requestOptions.transform(
                        new RoundedCornersTransformation(loadConfig.getRoundRadius(), 0));
            }
            requestBuilder.apply(requestOptions).into(imageView);
        }
    }
}
