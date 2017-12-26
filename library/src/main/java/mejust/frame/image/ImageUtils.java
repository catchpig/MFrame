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
 *  在Application中初始化ImageUtils.init(hostImageUrl,defalutImage,errorImage)
 */

public class ImageUtils {
    /**
     * 加载图片
     * @param imageView
     * @param url 地址
     */
    @BindingAdapter("imageUrl")
    public static void show(ImageView imageView, String url){
        GlideApp.with(imageView.getContext())
                .load(packUrl(url))
                .placeholder(sDefalutImage)
                .error(sErrorImage)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     * @param imageView
     * @param url 地址
     */
    public static void showCircle(ImageView imageView,String url){
        GlideApp.with(imageView.getContext())
                .load(packUrl(url))
                .placeholder(sDefalutImage)
                .error(sErrorImage)
                .transform(new CircleCrop())
                .into(imageView);
    }

    /**
     * 加载圆角图片
     * @param imageView
     * @param url 地址
     * @param raduisDp 圆角大小
     */
    public static void showRound(ImageView imageView,String url,float raduisDp){
        int raduis = ScreenUtils.dpToPxInt(imageView.getContext(),raduisDp);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        GlideApp.with(imageView.getContext())
                .load(packUrl(url))
                .placeholder(sDefalutImage)
                .error(sErrorImage)
                .transform(new RoundedCorners(raduis))
                .override(layoutParams.width,layoutParams.height)
                .into(imageView);

    }

    /**
     * 包装url
     * @param url
     * @return
     */
    public static String packUrl(String url){
        if (StringUtils.validateHttpOrHttps(url)) {
            return url;
        } else {
            return HOST_IMAGE_URL +url;
        }
    }

    /**
     * 加载错误的图片
     */
    private static int sErrorImage;

    /**
     *  设置加载失败后显示的图片
     * @param errorImage
     */
    public static void setErrorImage(@DrawableRes int errorImage){
        sErrorImage = errorImage;
    }

    /**
     * 正在加载中图片
     */
    private static int sDefalutImage;

    /**
     *  设置正在加载中显示的图片
     * @param defalutImage
     */
    public static void setDefalutImage(@DrawableRes int defalutImage){
        sDefalutImage = defalutImage;
    }
    /**
     * 图片域名地址
     */
    public static String HOST_IMAGE_URL;

    /**
     * 设置图片域名地址
     * @param hostImageUrl
     */
    public static void setHostImageUrl(String hostImageUrl){
        HOST_IMAGE_URL = hostImageUrl;
    }

    /**
     * 初始化
     * @param hostImageUrl 图片域名地址
     * @param defalutImage 加载中显示的图片
     * @param errorImage 加载错误显示的图片
     */
    public static void init(String hostImageUrl,@DrawableRes int defalutImage,@DrawableRes int errorImage){
        HOST_IMAGE_URL = hostImageUrl;
        sDefalutImage = defalutImage;
        sErrorImage = errorImage;
    }
}
