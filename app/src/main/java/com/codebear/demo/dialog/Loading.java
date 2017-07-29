package com.codebear.demo.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.codebear.demo.R;
import com.codebear.demo.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/6.
 */

public class Loading extends BaseDialogFragment {

    private static Loading loading;
    @BindView(R.id.lv_circularZoom)
    LVGhost lvCircularZoom;

    public static Loading newInstance(boolean canCancelable) {
        loading = new Loading();
        Bundle bundle = new Bundle();
        bundle.putBoolean("canCancelable", canCancelable);
        loading.setArguments(bundle);
        return loading;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = loading.getArguments();
        boolean canCancelable = bundle.getBoolean("canCancelable");
        setCancelable(canCancelable);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_loading, container, false);
        ButterKnife.bind(this, view);
        lvCircularZoom.startAnim();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        lvCircularZoom.stopAnim();
        loading = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (null != dialog) {
            Window window = dialog.getWindow();
            if(null != window) {
                window.setGravity(Gravity.CENTER);
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }
    }
}
