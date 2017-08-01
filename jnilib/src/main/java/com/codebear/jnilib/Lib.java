package com.codebear.jnilib;

/**
 * description:
 * <p>
 * Created by CodeBear on 2017/7/31.
 */

public class Lib {
    static {
        System.loadLibrary("JniTest");
    }
    public static native String getHelloWorld();
    public static native long getNum(int[] arr, int num);
}
