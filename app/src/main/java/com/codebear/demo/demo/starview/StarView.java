package com.codebear.demo.demo.starview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.codebear.demo.R;
import com.codebear.demo.utils.L;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/8/3.
 */

public class StarView extends View {

    /**
     * 一些默认的数值
     */
    private static final int DEFAULT_STAR_SIZE = 20;
    private static final int DEFAULT_STAR_POINT_COUNT = 5;
    private static final int DEFAULT_STAR_COUNT = 1;
    private static final int DEFAULT_STAR_STROKE_WIDTH = 1;
    private static final int DEFAULT_STAR_SPACE = 0;
    private static final float DEFAULT_STAR_MAX_PROGRESS = 100;
    private static final float DEFAULT_STAR_PROGRESS = 0;
    private static final boolean DEFAULT_SHOW_STAR_STROKE = true;
    private static final int DEFAULT_STAR_STROKE_COLOR = Color.RED;
    private static final int DEFAULT_STAR_FILL_COLOR = Color.WHITE;
    private static final int DEFAULT_STAR_COVER_COLOR = Color.RED;

    /**
     * 画星星边框的画笔
     */
    private Paint starStrokePaint = new Paint();
    /**
     * 画星星并填充颜色的画笔
     */
    private Paint starFillPaint = new Paint();
    /**
     * 覆盖星星上的颜色的画笔(也就是绘制进度)
     */
    private Paint starCoverPaint = new Paint();
    /**
     * 用该画笔才能将要覆盖的颜色(进度)填充到不规则的星星上
     */
    private Paint starPaint = new Paint();

    /**
     * 一个星星的宽度(宽高一样)
     */
    private int starSize;
    /**
     * 星星的点数，也就是星星的角数
     */
    private int starPointCount;
    /**
     * 星星的个数
     */
    private int starCount;
    /**
     * 星星边框的宽度
     */
    private int starStrokeWidth;
    /**
     * 星星的间距
     */
    private int starSpace;
    /**
     * 最大的进度
     */
    private float starMaxProgress;
    /**
     * 当前显示的进度
     */
    private float starProgress;
    /**
     * 是否显示星星边框
     */
    private boolean showStarStroke;
    /**
     * 星星边框的颜色
     */
    private int starStrokeColor;
    /**
     * 填充星星的颜色
     */
    private int starFillColor;
    /**
     * 覆盖星星(进度)的颜色
     */
    private int starCoverColor;

    public StarView(Context context) {
        this(context, null);
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);
        setupStrokePaint();
        setupFillPaint();
        setupCoverPaint();

        starPaint.setAntiAlias(true);
        starPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StarView);

        starSize = array.getDimensionPixelOffset(R.styleable.StarView_starSize, DEFAULT_STAR_SIZE);
        starPointCount = array.getInteger(R.styleable.StarView_starPointCount, DEFAULT_STAR_POINT_COUNT);
        starCount = array.getInteger(R.styleable.StarView_starCount, DEFAULT_STAR_COUNT);
        starSpace = array.getDimensionPixelOffset(R.styleable.StarView_starSpace, DEFAULT_STAR_SPACE);
        starStrokeWidth = array.getDimensionPixelOffset(R.styleable.StarView_starStrokeWidth,
                DEFAULT_STAR_STROKE_WIDTH);
        starMaxProgress = array.getFloat(R.styleable.StarView_starMaxProgress, DEFAULT_STAR_MAX_PROGRESS);
        starProgress = array.getFloat(R.styleable.StarView_starProgress, DEFAULT_STAR_PROGRESS);
        showStarStroke = array.getBoolean(R.styleable.StarView_showStarStroke, DEFAULT_SHOW_STAR_STROKE);
        starStrokeColor = array.getColor(R.styleable.StarView_starStrokeColor, DEFAULT_STAR_STROKE_COLOR);
        starFillColor = array.getColor(R.styleable.StarView_starFillColor, DEFAULT_STAR_FILL_COLOR);
        starCoverColor = array.getColor(R.styleable.StarView_starCoverColor, DEFAULT_STAR_COVER_COLOR);

        starProgress = Math.max(starProgress, 0);
        starProgress = Math.min(starProgress, starMaxProgress);

        array.recycle();
    }

    private void setupStrokePaint() {
        starStrokePaint.setStyle(Paint.Style.STROKE);
        starStrokePaint.setAntiAlias(true);
        starStrokePaint.setStrokeWidth(starStrokeWidth);
        starStrokePaint.setColor(starStrokeColor);
    }

    private void setupFillPaint() {
        starFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        starFillPaint.setAntiAlias(true);
        starFillPaint.setColor(starFillColor);
    }

    private void setupCoverPaint() {
        starCoverPaint.setStyle(Paint.Style.FILL);
        starCoverPaint.setAntiAlias(true);
        starCoverPaint.setColor(starCoverColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float preStar = starMaxProgress / starCount;
        float nowProgress = starProgress;

        int x = 0;
        for(int i = 0;i < starCount;++i) {
            if(nowProgress >= preStar) {
                draw(canvas, x, starSize);
                nowProgress -= preStar;
            } else {
                draw(canvas, x, nowProgress / preStar * starSize);
                nowProgress = 0;
            }
            x += starSize + starSpace;
        }
    }

    /**
     * 将星星绘制到真正的画布上
     *
     * @param canvas   要显示的画布
     * @param x        绘制起点x
     * @param progress 进度
     */
    private void draw(Canvas canvas, int x, float progress) {
        //将已经填充了颜色的星星绘制到画布上
        canvas.drawBitmap(getStarBitmap(progress), x, 0, null);
        if (showStarStroke) {
            //给星星添加边框
            drawStar(canvas, starStrokePaint, starSize / 2 + x, starSize / 2);
        }
    }

    /**
     * 获取已经绘制了进度的星星的bitmap对象
     *
     * @param dx 进度
     *
     * @return
     */
    private Bitmap getStarBitmap(float dx) {
        //将星星绘制到star上
        Bitmap star = Bitmap.createBitmap(starSize, starSize, Bitmap.Config.ARGB_8888);
        Canvas starCanvas = new Canvas(star);
        drawStar(starCanvas, starFillPaint, starSize / 2, starSize / 2);

        //在star上填充颜色
        Bitmap finalStar = Bitmap.createBitmap(starSize, starSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(finalStar);
        canvas.drawRect(0, 0, dx, starSize, starCoverPaint);
        canvas.drawBitmap(star, 0, 0, starPaint);

        return finalStar;
    }

    /**
     * 绘制星星
     *
     * @param canvas 画布
     * @param paint  画笔
     * @param dx     起点x
     * @param dy     起点y
     */
    private void drawStar(Canvas canvas, Paint paint, float dx, float dy) {
        canvas.translate(dx, dy);
        canvas.rotate(-90);

        Path path = getStarPath();
        canvas.drawPath(path, paint);

        canvas.rotate(90);
        canvas.translate(-dx, -dy);
    }

    /**
     * 获取星星的path
     *
     * @return
     */
    private Path getStarPath() {
        Path path = new Path();
        float radius;
        if (starPointCount % 2 == 0) {
            radius = starSize * (cos(360.0 / starPointCount / 2) / 2 - sin(360.0 / starPointCount / 2) * sin(90 -
                    360.0 / starPointCount) / cos(90 - 360.0 / starPointCount));
        } else {
            radius = starSize * sin(360.0 / starPointCount / 4) / 2 / sin(180 - 360.0 / starPointCount / 2 - 360.0 /
                    starPointCount / 4);
        }
        for (int i = 0; i < starPointCount; ++i) {
            if (i == 0) {
                path.moveTo(starSize * cos(360 / starPointCount * i) / 2, starSize * sin(360 / starPointCount * i) / 2);
            } else {
                path.lineTo(starSize * cos(360.0 / starPointCount * i) / 2, starSize * sin(360.0 / starPointCount *
                        i) / 2);
            }
            path.lineTo(radius * cos(360.0 / starPointCount * i + 360.0 / starPointCount / 2), radius * sin(360.0 /
                    starPointCount * i + 360.0 / starPointCount / 2));
        }
        path.close();
        return path;
    }

    private float sin(double num) {
        return (float) Math.sin(num * Math.PI / 180);
    }

    private float cos(double num) {
        return (float) Math.cos(num * Math.PI / 180);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        float result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = starSize * starCount + (starCount - 1) * starSpace;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
            L.i(starSize + " -- " + starCount);
        }

        return (int) result;
    }

    private int measureHeight(int measureSpec) {
        float result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = starSize;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return (int) result;
    }

    public void setStarSize(int starSize) {
        this.starSize = starSize;
        requestLayout();
        invalidate();
    }

    public void setStarPointCount(int starPointCount) {
        this.starPointCount = starPointCount;
        requestLayout();
        invalidate();
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
        requestLayout();
        invalidate();
    }

    public void setStarStrokeWidth(int starStrokeWidth) {
        this.starStrokeWidth = starStrokeWidth;
        setupStrokePaint();
        requestLayout();
        invalidate();
    }

    public void setStarSpace(int starSpace) {
        this.starSpace = starSpace;
        requestLayout();
        invalidate();
    }

    public void setStarMaxProgress(float starMaxProgress) {
        this.starMaxProgress = starMaxProgress;
        invalidate();
    }

    public void setStarProgress(float starProgress) {
        this.starProgress = starProgress;
        this.starProgress = Math.max(this.starProgress, 0);
        this.starProgress = Math.min(this.starProgress, starMaxProgress);
        invalidate();
    }

    public void setShowStarStroke(boolean showStarStroke) {
        this.showStarStroke = showStarStroke;
        invalidate();
    }

    public void setStarStrokeColor(int starStrokeColor) {
        this.starStrokeColor = starStrokeColor;
        setupStrokePaint();
        invalidate();
    }

    public void setStarFillColor(int starFillColor) {
        this.starFillColor = starFillColor;
        setupFillPaint();
        invalidate();
    }

    public void setStarCoverColor(int starCoverColor) {
        this.starCoverColor = starCoverColor;
        setupCoverPaint();
        invalidate();
    }

    public int getStarSize() {
        return starSize;
    }

    public int getStarPointCount() {
        return starPointCount;
    }

    public int getStarCount() {
        return starCount;
    }

    public int getStarStrokeWidth() {
        return starStrokeWidth;
    }

    public int getStarSpace() {
        return starSpace;
    }

    public float getStarMaxProgress() {
        return starMaxProgress;
    }

    public float getStarProgress() {
        return starProgress;
    }

    public boolean isShowStarStroke() {
        return showStarStroke;
    }

    public int getStarStrokeColor() {
        return starStrokeColor;
    }

    public int getStarFillColor() {
        return starFillColor;
    }

    public int getStarCoverColor() {
        return starCoverColor;
    }
}
