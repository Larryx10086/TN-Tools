package com.celltick.apac.news.customizedview;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Glide配置类
 * 注意，@GlideModule注解和AppGlideModule一起出现，否则此类无法被发现
 */
@GlideModule
public final class MyGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        //获取设备推荐缓存大小
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2)
                .build();
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
        //也可以自己设置一个内存缓存大小 此处50MB
        builder.setMemoryCache(new LruResourceCache(1024*1024*50));
        //设置手机内置磁盘缓存大小，并且制定缓存路径，此处100MB
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
                "cacheFolderName",
                1024*1024*100));
        //设置手机外置磁盘缓存大小，SD卡，已经不建议使用，因为大多数Android手机已经不支持SD卡了
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,
                "cacheFolderName",
                1024*1024*100));
    }
    /**
     * 设置清单解析，设置为false，避免添加相同的modules两次
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}