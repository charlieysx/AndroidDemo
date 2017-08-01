//
// Created by GeekBeans on 2017/7/31.
//
#include<com_codebear_jnilib_Lib.h>

JNIEXPORT jstring JNICALL Java_com_codebear_jnilib_Lib_getHelloWorld(JNIEnv* env, jclass obj){
    return env->NewStringUTF("Hello");
}

JNIEXPORT jlong JNICALL Java_com_codebear_jnilib_Lib_getNum(JNIEnv * env, jclass obj, jintArray j_array, jint num) {

    jint j, i;
    jlong sum = 0;
    jint *c_array;
    jint arr_len;
    //1. 获取数组长度
    arr_len = env->GetArrayLength(j_array);
    c_array = (jint*)malloc(sizeof(jint) *arr_len);
    //3. 初始化缓冲区
    memset(c_array, 0, sizeof(jint) *arr_len);
    //4. 拷贝Java数组中的所有元素到缓冲区中
    env->GetIntArrayRegion(j_array, 0, arr_len, c_array);
    for(j = 0;j < num;++j) {
        for (i = 0; i < arr_len; i++) {
            sum += c_array[i];  //5. 累加数组元素的和
        }
    }
    free(c_array);  //6. 释放存储数组元素的缓冲区
    return sum;
}