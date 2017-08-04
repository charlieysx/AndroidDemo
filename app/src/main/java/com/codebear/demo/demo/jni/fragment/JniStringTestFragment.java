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

    private int[] array;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jni_string_test;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        tvShow.setText(Lib.getHelloWorld());

        array = new int[1000001];
        for (int i = 0; i < 1000001; ++i) {
            array[i] = i;
        }

        sumFromJava();
        sumFromC();
    }

    private long sum1 = 0, sum2 = 0, start1, start2;

    private void sumFromJava() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                start1 = System.currentTimeMillis();
                for (int j = 0; j < 1000; ++j) {
                    for (int i = 0; i < 1000001; ++i) {
                        sum1 += array[i];
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (tvShow != null) {
                            tvShow.append("\nsumFromJava:" + sum1 + "\ntime:" + ((System.currentTimeMillis() -
                                    start1) / 1000.0) + "s");
                        }
                    }
                });
            }
        }).start();
    }

    private void sumFromC() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                start2 = System.currentTimeMillis();
                sum2 = Lib.getNum(array, 1000);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (tvShow != null) {
                            tvShow.append("\nsumFromC:" + sum2 + "\ntime:" + ((System.currentTimeMillis() - start2) /
                                    1000.0) + "s");
                        }
                    }
                });
            }
        }).start();
    }
}
