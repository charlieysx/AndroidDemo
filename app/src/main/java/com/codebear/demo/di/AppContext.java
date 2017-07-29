package com.codebear.demo.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/6.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AppContext {
}
