package com.codebear.demo.demo.face_detection.fragment;

import android.graphics.Bitmap;
import android.media.FaceDetector;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codebear.demo.R;
import com.codebear.demo.base.BaseFragment;
import com.codebear.demo.demo.face_detection.custom_view.FaceView;
import com.codebear.demo.utils.BitmapUtil;
import com.codebear.demo.utils.T;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/7/29.
 */

public class FaceDetectionFragment extends BaseFragment {

    @BindView(R.id.fv_img)
    FaceView fvImg;

    private int maxFace = 4;
    private Bitmap img = null;
    private FaceDetector mFaceDetector;
    /**
     * 存储识别的脸
     */
    private FaceDetector.Face[] mFaces = new FaceDetector.Face[maxFace];
    /**
     * 真实检测到的人脸数
     */
    private int faceNum = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_face_detection;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }

    @OnClick(R.id.tv_add)
    public void onAddClick() {
        showLoading(false);
        img = null;
        Glide.with(this)
                .load("http://star.kuwo.cn/star/starheads/180/39/41/2312174491.jpg")
                .asBitmap()
                .dontAnimate()
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        int imgSize = (int) getResources().getDimension(R.dimen.img_size);
                        resource = BitmapUtil.zoomByWH(resource, imgSize, imgSize);
                        fvImg.clear();
                        fvImg.setImageBitmap(resource);
                        img = resource;
                        showSuccessMsg(null, null);
                    }
                });
    }

    @OnClick(R.id.tv_start)
    public void onStartClick() {
        if(img == null) {
            T.s(mContext, "图片未加载");
            return;
        }
        showLoading(false);
        Observable
                .create(new Observable.OnSubscribe<Bitmap>() {
                    @Override
                    public void call(Subscriber<? super Bitmap> subscriber) {
                        //拿到需要识别的图片
                        int imageWidth = img.getWidth();
                        int imageHeight = img.getHeight();
                        //创建FaceDetector
                        mFaceDetector = new FaceDetector(imageWidth, imageHeight, maxFace);
                        //开始检测，并将检测到的人脸存到mFaces数组中
                        faceNum = mFaceDetector.findFaces(img, mFaces);
                        subscriber.onNext(null);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Bitmap o) {
                        if(faceNum > 0) {
                            fvImg.setFaces(mFaces, faceNum);
                            T.s(mContext, "检测到" + faceNum + "张人脸");
                        } else {
                            T.s(mContext, "未检测到人脸");
                        }
                        showSuccessMsg(null, null);
                    }
                });
    }
}
