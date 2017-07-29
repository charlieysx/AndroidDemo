package com.codebear.demo.di.component;

import com.codebear.demo.di.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/6.
 */

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {

    class Instance {
        private static ApiComponent apiComponent;

        public static void init(ApiComponent component) {
            apiComponent = component;
        }

        public static ApiComponent get() {
            return apiComponent;
        }
    }
}
