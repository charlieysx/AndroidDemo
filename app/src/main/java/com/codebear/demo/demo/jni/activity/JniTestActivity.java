package com.codebear.demo.demo.jni.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.codebear.demo.R;
import com.codebear.demo.base.BaseActivity;
import com.codebear.demo.demo.jni.fragment.JniStringTestFragment;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/7/29.
 */

public class JniTestActivity extends BaseActivity {
    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_container, new JniStringTestFragment());
        ft.commit();
    }
}
