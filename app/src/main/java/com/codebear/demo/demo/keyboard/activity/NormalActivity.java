package com.codebear.demo.demo.keyboard.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.codebear.demo.R;
import com.codebear.demo.base.BaseActivity;
import com.codebear.demo.demo.keyboard.util.Keyboard;
import com.codebear.demo.demo.keyboard.util.KeyboardListener;
import com.codebear.demo.demo.keyboard.util.NavListener;

import butterknife.BindView;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/8/1.
 */

public class NormalActivity extends BaseActivity {

    @BindView(R.id.tv_result_keyboard)
    TextView tvResultKeyboard;
    @BindView(R.id.tv_result_nav)
    TextView tvResultNav;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_keyboard;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        new Keyboard(this)
                .setNavListener(new NavListener() {
                    @Override
                    public void onNavOpen(int height) {
                        String text = "底部导航栏弹起 : " + height;
                        tvResultNav.setText(text);
                    }

                    @Override
                    public void onNavClose() {
                        tvResultNav.setText("底部导航栏关闭");
                    }
                })
                .setKeyboardListener(new KeyboardListener() {
                    @Override
                    public void onKeyboardOpen(int height) {
                        String text = "键盘弹起 : " + height;
                        tvResultKeyboard.setText(text);
                    }

                    @Override
                    public void onKeyboardClose() {
                        tvResultKeyboard.setText("键盘关闭");
                    }
                })
                .listener();
    }
}
