package mejust.frame.image;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 创建时间:2017/12/20 14:39<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/20 14:39<br/>
 * 描述:Glide注解
 */
@GlideModule
public class MAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        /**
         * 设置内存缓存
         * 获取系统分配给应用的总内存大小
         * 设置图片内存缓存占用八分之一
         */
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int memoryCacheSize = maxMemory / 8;
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        /**
         * 设置磁盘缓存为50M
         */
        int diskCacheSize = 50*1024*1024;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,diskCacheSize));
    }
}
