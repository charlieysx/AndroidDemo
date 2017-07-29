package com.codebear.demo.utils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

/**
 * description: 打印日志的辅助类
 * <p>
 * Created by CodeBear on 17/6/5.
 */

public class L {
    public static Gson gson = new Gson();

    public static void init() {
        Logger.init("AndroidDemo");
    }

    public static void i(Object o) {
        Logger.i(gson.toJson(o));
    }

    public static void d(Object o) {
        Logger.d(gson.toJson(o));
    }

    public static void e(Object o) {
        Logger.e(gson.toJson(o));
    }

    public static void v(Object o) {
        Logger.v(gson.toJson(o));
    }

    public static void i(String tag, Object o) {
        Logger.t(tag).i(gson.toJson(o));
    }

    public static void d(String tag, Object o) {
        Logger.t(tag).d(gson.toJson(o));
    }

    public static void e(String tag, Object o) {
        Logger.t(tag).e(gson.toJson(o));
    }

    public static void v(String tag, Object o) {
        Logger.t(tag).v(gson.toJson(o));
    }
}
