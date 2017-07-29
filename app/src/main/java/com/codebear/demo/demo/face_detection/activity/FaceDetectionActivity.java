package com.codebear.demo.demo.face_detection.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.codebear.demo.R;
import com.codebear.demo.base.BaseActivity;
import com.codebear.demo.demo.face_detection.fragment.FaceDetectionFragment;

/**
 * description: 人脸检测
 * <p>
 * Created by CodeBear on 2017/7/29.
 */

public class FaceDetectionActivity extends BaseActivity {

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_container, new FaceDetectionFragment());
        ft.commit();
    }
}
