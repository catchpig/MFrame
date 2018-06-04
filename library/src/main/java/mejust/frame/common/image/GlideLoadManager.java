package mejust.frame.common.image;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import conm.zhuazhu.common.utils.StringUtils;
import java.io.File;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import mejust.frame.common.log.Logger;

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
                Logger.e("image url is incomplete,and app host url is null,url address is" + url);
                realUrl = url;
            } else {
                realUrl = imageConfig.getHostUrl() + url;
            }
        }
        requestImageReal(Glide.with(imageView).load(realUrl), loadConfig, imageView);
    }

    @Override
    public void loadLocal(ImageView imageView, File file) {
        loadLocal(imageView, file, null);
    }

    @Override
    public void loadLocal(ImageView imageView, File file, ImageLoadConfig loadConfig) {
        requestImageReal(Glide.with(imageView).load(file), loadConfig, imageView);
    }

    @Override
    public void loadAsset(ImageView imageView, String assetName) {
        loadAsset(imageView, assetName, null);
    }

    @Override
    public void loadAsset(ImageView imageView, String assetName, ImageLoadConfig loadConfig) {
        requestImageReal(Glide.with(imageView).load(ASSET_FILE_HOST + assetName), loadConfig,
                imageView);
    }

    private void requestImageReal(RequestBuilder<Drawable> requestBuilder,
            ImageLoadConfig loadConfig, ImageView imageView) {
        RequestBuilder<Drawable> requestBuilderReal = requestBuilder;
        RequestOptions requestOptions = new RequestOptions();
        if (imageConfig == null) {
            if (loadConfig == null) {
                requestOptions = null;
            } else {
                if (loadConfig.getPlaceholderResId() > 0) {
                    requestOptions = requestOptions.placeholder(loadConfig.getPlaceholderResId())
                            .fallback(loadConfig.getPlaceholderResId());
                }
                if (loadConfig.getErrorResId() > 0) {
                    requestOptions = requestOptions.error(loadConfig.getErrorResId());
                    requestBuilderReal = requestBuilderReal.error(Glide.with(imageView)
                            .load(ContextCompat.getDrawable(imageView.getContext(),
                                    loadConfig.getErrorResId())));
                }
                if (loadConfig.isShowCircle()) {
                    requestOptions = requestOptions.circleCrop();
                }
                if (loadConfig.getRoundRadius() > 0) {
                    requestOptions = requestOptions.transform(
                            new RoundedCornersTransformation(loadConfig.getRoundRadius(), 0));
                }
            }
        } else {
            if (loadConfig == null) {
                if (imageConfig.getPlaceholderResId() > 0) {
                    requestOptions = requestOptions.placeholder(imageConfig.getPlaceholderResId())
                            .fallback(imageConfig.getPlaceholderResId());
                }
                if (imageConfig.getErrorResId() > 0) {
                    requestOptions = requestOptions.error(imageConfig.getErrorResId());
                    requestBuilderReal = requestBuilderReal.error(Glide.with(imageView)
                            .load(ContextCompat.getDrawable(imageView.getContext(),
                                    imageConfig.getErrorResId())));
                }
            } else {
                if (loadConfig.getPlaceholderResId() > 0) {
                    requestOptions = requestOptions.placeholder(loadConfig.getPlaceholderResId())
                            .fallback(loadConfig.getPlaceholderResId());
                } else if (imageConfig.getPlaceholderResId() > 0) {
                    requestOptions = requestOptions.placeholder(imageConfig.getPlaceholderResId())
                            .fallback(imageConfig.getPlaceholderResId());
                }
                if (loadConfig.getErrorResId() > 0) {
                    requestOptions = requestOptions.error(loadConfig.getErrorResId());
                    requestBuilderReal = requestBuilderReal.error(Glide.with(imageView)
                            .load(ContextCompat.getDrawable(imageView.getContext(),
                                    loadConfig.getErrorResId())));
                } else if (imageConfig.getErrorResId() > 0) {
                    requestOptions = requestOptions.error(imageConfig.getErrorResId());
                    requestBuilderReal = requestBuilderReal.error(Glide.with(imageView)
                            .load(ContextCompat.getDrawable(imageView.getContext(),
                                    imageConfig.getErrorResId())));
                }
                if (loadConfig.isShowCircle()) {
                    requestOptions = requestOptions.circleCrop();
                }
                if (loadConfig.getRoundRadius() > 0) {
                    requestOptions = requestOptions.transform(
                            new RoundedCornersTransformation(loadConfig.getRoundRadius(), 0));
                }
            }
        }
        if (requestOptions == null) {
            requestBuilderReal.into(imageView);
        } else {
            requestBuilderReal.apply(requestOptions).into(imageView);
        }
    }
}
