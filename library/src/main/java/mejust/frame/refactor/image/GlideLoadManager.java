package mejust.frame.refactor.image;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

    private ImageConfig imageConfig;

    public GlideLoadManager(ImageConfig imageConfig) {
        this.imageConfig = imageConfig;
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
            if (imageConfig == null) {
                throw new IllegalArgumentException("image load config not init");
            } else {
                realUrl = imageConfig.getHostUrl() + url;
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

    private void requestImage(RequestBuilder<Drawable> requestBuilder, ImageLoadConfig loadConfig,
            ImageView imageView) {
        if (loadConfig == null) {
            if (imageConfig == null) {
                requestBuilder.into(imageView);
            } else {
                RequestOptions requestOptions =
                        new RequestOptions().fallback(new ColorDrawable(Color.GRAY));
                if (imageConfig.getPlaceholderResId() > 0) {
                    requestOptions = requestOptions.placeholder(imageConfig.getPlaceholderResId());
                }
                if (imageConfig.getErrorResId() > 0) {
                    requestOptions = requestOptions.error(imageConfig.getErrorResId());
                }
                requestBuilder.apply(requestOptions)
                        .error(Glide.with(imageView).load(new ColorDrawable(Color.GRAY)))
                        .into(imageView);
            }
        } else {
            RequestOptions requestOptions =
                    new RequestOptions().fallback(new ColorDrawable(Color.GRAY));
            if (loadConfig.getPlaceholderResId() > 0) {
                requestOptions = requestOptions.placeholder(imageConfig.getPlaceholderResId());
            }
            if (loadConfig.getErrorResId() > 0) {
                requestOptions = requestOptions.error(imageConfig.getErrorResId());
            }
            if (loadConfig.isShowCircle()) {
                requestOptions = requestOptions.circleCrop();
            }
            if (loadConfig.getRoundRadius() > 0) {
                requestOptions = requestOptions.transform(
                        new RoundedCornersTransformation(loadConfig.getRoundRadius(), 0));
            }
            requestBuilder.apply(requestOptions)
                    .error(Glide.with(imageView).load(new ColorDrawable(Color.GRAY)))
                    .into(imageView);
        }
    }
}
