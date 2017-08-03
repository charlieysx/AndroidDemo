package com.codebear.demo.demo.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.codebear.demo.R;
import com.codebear.demo.base.BaseActivity;
import com.codebear.demo.utils.T;

import butterknife.BindView;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/8/2.
 */

public class ToolbarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    CBToolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_toolbar;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        toolbar.setTitleText("toolbar")
                .setToolbarColor(Color.parseColor("#000000"))
                .setTitleColor(Color.parseColor("#FFFFFF"))
                .setShadowTopColor(Color.parseColor("#44000000"))
                .setOnLeftIconClickListener(new CBToolbar.OnLeftIconClickListener() {
                    @Override
                    public void onIconClick(View v) {
                        T.s(ToolbarActivity.this, "click Icon");
                    }
                });
    }
}
