package com.codebear.demo.demo.face_detection.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/7/29.
 */

public class FaceView extends AppCompatImageView {

    /**
     * 存储识别的脸
     */
    private FaceDetector.Face[] mFaces;
    private int faceNum;
    private PointF pointF;
    private Paint paint;

    public FaceView(Context context) {
        this(context, null);
    }

    public FaceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#FF0000"));

        pointF = new PointF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mFaces != null) {
            for(int i = 0;i < faceNum;++i) {
                FaceDetector.Face face = mFaces[i];
                face.getMidPoint(pointF);
                float myEyesDistance = face.eyesDistance();//得到人脸中心点和眼间距离参数
                canvas.drawRect(
                        pointF.x - myEyesDistance,
                        pointF.y - myEyesDistance,
                        pointF.x + myEyesDistance,
                        (float) (pointF.y + myEyesDistance * 1.5),
                        paint);
            }
        }
    }

    public void setFaces(FaceDetector.Face[] faces, int faceNum) {
        mFaces = faces;
        this.faceNum = faceNum;
        invalidate();
    }

    public void clear() {
        mFaces = null;
        invalidate();
    }
}
