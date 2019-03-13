package com.beibei.learnglide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author: anbeibei
 * <p>
 * date: 2019/3/13
 * <p>
 * desc:
 */
@GlideExtension
public class MyGlideExtension {


    private MyGlideExtension() {
    }

    /**
     * 定制自己的API
     * 我们要求项目中所有图片的缓存策略全部都要缓存原始图片
     */
    @GlideOption
    public static void cacheSource(RequestOptions options){
        options.diskCacheStrategy(DiskCacheStrategy.DATA);
    }
}
