package com.codebear.demo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/12.
 */

public class BitmapUtil {
    /**
     * drawable转为bitmap
     *
     * @param drawable 原图
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (null == drawable) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable
                .getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    /**
     * 将bitmap缩放,默认缩放为原来的1/8
     *
     * @param bitmap 原图
     * @return
     */
    public static Bitmap zoom(Bitmap bitmap) {
        return zoomByScale(bitmap, 8.0f, 8.0f);
    }

    /**
     * 将bitmap缩放
     *
     * @param bitmap 原图
     * @param scaleX width的缩放比例
     * @param scaleY height的缩放比例
     * @return
     */
    public static Bitmap zoomByScale(Bitmap bitmap, float scaleX, float scaleY) {
        if (null == bitmap) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 按指定宽高将bitmap缩放
     *
     * @param bitmap    原图
     * @param newWidth  新的宽度
     * @param newHeight 新的高度
     * @return
     */
    public static Bitmap zoomByWH(Bitmap bitmap, float newWidth, float newHeight) {
        if (null == bitmap) {
            return null;
        }
        // 获得图片的宽高
        int oldWidth = bitmap.getWidth();
        int oldHeight = bitmap.getHeight();
        // 计算缩放比例
        float scaleWidth = newWidth / oldWidth;
        float scaleHeight = newHeight / oldHeight;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return Bitmap.createBitmap(bitmap, 0, 0, oldWidth, oldHeight, matrix, true);
    }
}
