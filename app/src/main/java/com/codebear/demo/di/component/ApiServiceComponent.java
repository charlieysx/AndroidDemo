package com.codebear.demo.di.component;

import com.codebear.demo.di.module.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/6.
 */

@Singleton
@Component(modules = {ApiServiceModule.class})
public interface ApiServiceComponent {

    class Instance {
        private static ApiServiceComponent apiServiceComponent;

        public static void init(ApiServiceComponent component) {
            apiServiceComponent = component;
        }

        public static ApiServiceComponent get() {
            return apiServiceComponent;
        }
    }
}
