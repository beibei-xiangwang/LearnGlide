package com.beibei.learnglide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * @author: anbeibei
 * <p>
 * date: 2019/3/13
 * <p>
 * desc: 自定义模块
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    /**
     * 更改Glide配置
     */
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
    }

    /**
     * 替换Glide组件
     */
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }
}
