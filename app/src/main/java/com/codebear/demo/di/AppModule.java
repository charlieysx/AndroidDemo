package com.codebear.demo.di;

import android.content.Context;

import com.codebear.demo.DemoApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/6.
 */

@Module
public class AppModule {

    private final DemoApplication demoApplication;

    public AppModule(DemoApplication demoApplication) {
        this.demoApplication = demoApplication;
    }


    @Provides
    @Singleton
    @AppContext
    public Context provideAppContext() {
        return demoApplication;
    }
}
