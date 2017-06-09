package org.zzy.news.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.zzy.news.R;
import org.zzy.news.moudle.application.MyApplication;

/**
 * Created by zhaoziying on 2017/4/27.
 */

public class ImageLoaderUtil {
    private static ImageLoaderUtil instance = new ImageLoaderUtil();
    private static MyTransformation myTransformation;


    public static ImageLoaderUtil getInstance(){
        return instance;
    }
    public ImageLoaderUtil() {
        myTransformation = new MyTransformation(MyApplication.context,CommonUtils.px2sp(MyApplication.context,5));
    }

    public static void clearCache(){
        Glide.get(MyApplication.context).clearMemory();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(MyApplication.context).clearDiskCache();
            }
        }).start();
    }

    public static void showPic(Context context,
                               String url, ImageView imageView, int errorResId, int placeholderResId, Drawable placeholder){
        if(placeholder!=null){
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade()
                    .placeholder(placeholder)
                    .error(errorResId)
                    .priority(Priority.NORMAL)
                    .dontAnimate()
                    .transform(myTransformation)
                    .into(imageView);
        }else {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade()
                    .placeholder(placeholderResId)
                    .error(errorResId)
                    .dontAnimate()
                    .transform(myTransformation)
                    .priority(Priority.NORMAL)
                    .into(imageView);
        }
    }

    public static void showPic(Context context, String url, SimpleTarget target, int errorResId){
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade()
                    .placeholder(errorResId)
                    .error(errorResId)
                    .dontAnimate()
                    .transform(myTransformation)
                    .priority(Priority.NORMAL)
                    .into(target);
    }



    public static void showPic(Context context, String url, ImageView imageView){
        showPic(context,url,imageView, R.drawable.small_image_failed,R.drawable.small_image_failed,null);
    }

    public static void showPic(Context context, String url, ImageView imageView, Drawable placeholder){
        showPic(context,url,imageView, R.drawable.small_image_failed,R.drawable.small_image_failed,placeholder);
    }
    public static void showPic(Context context, String url, ImageView imageView, int placeholder){
        showPic(context,url,imageView, R.drawable.small_image_failed,placeholder,null);
    }
    private static class MyTransformation extends BitmapTransformation {
        private static float radius = 0f;

        public MyTransformation(Context context, float radius) {
            super(context);
            this.radius = radius;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }

}
