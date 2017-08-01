package com.codebear.demo.demo.keyboard.util;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/8/1.
 */

public class Keyboard {
    private KeyboardListener keyboardListener;
    private NavListener navListener;

    public Keyboard setKeyboardListener(KeyboardListener keyboardListener) {
        this.keyboardListener = keyboardListener;
        return this;
    }

    public Keyboard setNavListener(NavListener navListener) {
        this.navListener = navListener;
        return this;
    }

    private Activity activity;
    private View rootView;
    private int oldHeight = -1;
    private int nowHeight = -1;
    private int screenHeight = 0;
    private boolean keyboardOpen = false;
    private boolean navOpen = false;

    public Keyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        this.activity = activity;
        rootView = activity.getWindow().getDecorView();
    }

    public Keyboard(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        activity = fragment.getActivity();
        rootView = activity.getWindow().getDecorView();
    }

    public Keyboard(android.app.Fragment fragment) {
        if (fragment == null) {
            return;
        }
        activity = fragment.getActivity();
        rootView = activity.getWindow().getDecorView();
    }

    public void listener() {
        if (rootView == null) {
            return;
        }
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int navHeight = KeyboardUtil.getNavHeight(activity);
                if (screenHeight == 0) {
                    //避免一开始进入页面时底部导航栏是打开的情况使得获取的屏幕高度错误
                    screenHeight = r.bottom + navHeight;
                }
                nowHeight = screenHeight - r.bottom;

                int keyboardHeight = nowHeight - navHeight;
                if(navHeight == 0 && navOpen) {
                    navOpen = false;
                    if(navListener != null) {
                        navListener.onNavClose();
                    }
                } else if(navHeight > 0 && !navOpen) {
                    navOpen = true;
                    if(navListener != null) {
                        navListener.onNavOpen(navHeight);
                    }
                }

                if(keyboardHeight == 0 && keyboardOpen) {
                    keyboardOpen = false;
                    if(keyboardListener != null) {
                        keyboardListener.onKeyboardClose();
                    }
                } else if(keyboardHeight > 0 && !keyboardOpen) {
                    keyboardOpen = true;
                    if(keyboardListener != null) {
                        keyboardListener.onKeyboardOpen(keyboardHeight);
                    }
                }
            }
        });
    }
}
