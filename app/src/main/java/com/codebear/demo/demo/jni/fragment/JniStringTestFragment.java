package com.codebear.demo.demo.jni.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.codebear.demo.R;
import com.codebear.demo.base.BaseFragment;
import com.codebear.jnilib.Lib;

import butterknife.BindView;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/7/29.
 */

public class JniStringTestFragment extends BaseFragment {

    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jni_string_test;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        tvShow.setText(Lib.getHelloWorld());
    }
}
