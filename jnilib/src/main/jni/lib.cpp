//
// Created by GeekBeans on 2017/7/31.
//
#include<com_codebear_jnilib_Lib.h>

JNIEXPORT jstring JNICALL Java_com_codebear_jnilib_Lib_getHelloWorld__
        (JNIEnv* env, jclass obj)
{
    return env->NewStringUTF( "Hello from JNI !");
}
