package com.codebear.demo;

import android.app.Application;

import com.codebear.demo.di.AppModule;
import com.codebear.demo.di.component.ApiComponent;
import com.codebear.demo.di.component.DaggerApiComponent;
import com.codebear.demo.utils.L;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/5.
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();

        //初始化日志打印
        L.init();
    }

    private void initComponent() {
        ApiComponent.Instance.init(DaggerApiComponent.builder().appModule(new AppModule(this)).build());
    }
}
