package com.beibei.learnglide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;


public class MainActivity extends AppCompatActivity {
    String url = "http://guolin.tech/book.png";
//        String url = "http://guolin.tech/test.gif";

    ImageView imageView;

    SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
        @Override
        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
            imageView.setImageDrawable(resource);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image_view);

        //预加载
        Glide.with(this)
                .load("http://guolin.tech/book.png")
                .preload();

        //使用Generated API
        GlideApp.with(this)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.error)
                .skipMemoryCache(true)
                .cacheSource()
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(Target.SIZE_ORIGINAL)
                .circleCrop()
                .into(imageView);
    }

    public void loadImage(View view) {


        RequestOptions options = new RequestOptions()
                //加载占位图
                .placeholder(R.drawable.ic_launcher_background)
                //失败占位图
                .error(R.drawable.error)
                //指定图片大小
//                .override(200,100)
//                .override(Target.SIZE_ORIGINAL)
//                //禁用内存缓存
//                .skipMemoryCache(true)
//                //禁用硬盘缓存
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //图片变换
//                .circleCrop()
                .transforms(new BlurTransformation(),new GrayscaleTransformation())
                ;

        Glide.with(this)
//                .asBitmap()
                .load(url)
                .apply(options)
                //监听Glide加载图片的状态
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
//                .into(simpleTarget);
    }

    public void downloadImage(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://www.guolin.tech/book.png";
                    final Context context = getApplicationContext();
                    FutureTarget<File> target = Glide.with(context)
                            .asFile()
                            .load(url)
                            .submit();
                    final File imageFile = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
