package mejust.frame.image;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import conm.zhuazhu.common.utils.ScreenUtils;
import conm.zhuazhu.common.utils.StringUtils;

/**
 * 创建时间:2017/12/20 14:39<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/20 14:39<br/>
 * 描述:网络图片异步加载工具<br/>
 * 在Application中初始化ImageUtils.init(hostImageUrl,defalutImage,errorImage)
 */

public class ImageUtils {

    /**
     * 加载错误的图片
     */
    private static int sErrorImage;
    /**
     * 正在加载中图片
     */
    private static int sDefaultImage;
    /**
     * 图片域名地址
     */
    private static String HOST_IMAGE_URL;

    /**
     * 初始化
     *
     * @param hostImageUrl 图片域名地址
     * @param defaultImage 加载中显示的图片
     * @param errorImage 加载错误显示的图片
     */
    public static void init(String hostImageUrl, @DrawableRes int defaultImage,
            @DrawableRes int errorImage) {
        HOST_IMAGE_URL = hostImageUrl;
        sDefaultImage = defaultImage;
        sErrorImage = errorImage;
    }

    /**
     * 设置加载失败后显示的图片
     */
    public static void setErrorImage(@DrawableRes int errorImage) {
        sErrorImage = errorImage;
    }

    /**
     * 设置正在加载中显示的图片
     */
    public static void setDefalutImage(@DrawableRes int defaultImage) {
        sDefaultImage = defaultImage;
    }

    /**
     * 设置图片域名地址
     */
    public static void setHostImageUrl(String hostImageUrl) {
        HOST_IMAGE_URL = hostImageUrl;
    }

    /**
     * 加载图片
     *
     * @param url 地址
     */
    @BindingAdapter("imageUrl")
    public static void show(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(packUrl(url))
                .placeholder(sDefaultImage)
                .error(sErrorImage)
                .into(imageView);
    }

    /**
     * 加载图片
     *
     * @param url 图片地址
     * @param defaultImage 默认图
     * @param errorImage 错误图
     */
    public static void show(ImageView imageView, String url, @DrawableRes int defaultImage,
            @DrawableRes int errorImage) {
        GlideApp.with(imageView.getContext())
                .load(packUrl(url))
                .placeholder(defaultImage)
                .error(errorImage)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param url 地址
     */
    public static void showCircle(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(packUrl(url))
                .placeholder(sDefaultImage)
                .error(sErrorImage)
                .transform(new CircleCrop())
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param url 图片地址
     * @param defaultImage 默认图
     * @param errorImage 错误图
     */
    public static void showCircle(ImageView imageView, String url, @DrawableRes int defaultImage,
            @DrawableRes int errorImage) {
        GlideApp.with(imageView.getContext())
                .load(packUrl(url))
                .placeholder(defaultImage)
                .error(errorImage)
                .transform(new CircleCrop())
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param url 地址
     * @param radiusDp 圆角大小
     */
    public static void showRound(ImageView imageView, String url, float radiusDp) {
        int radius = ScreenUtils.dpToPxInt(radiusDp);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        GlideApp.with(imageView.getContext())
                .load(packUrl(url))
                .placeholder(sDefaultImage)
                .error(sErrorImage)
                .transform(new RoundedCorners(radius))
                .override(layoutParams.width, layoutParams.height)
                .into(imageView);
    }

    /**
     * 包装url
     */
    public static String packUrl(String url) {
        if (StringUtils.validateHttpOrHttps(url)) {
            return url;
        } else {
            return HOST_IMAGE_URL + url;
        }
    }
}
