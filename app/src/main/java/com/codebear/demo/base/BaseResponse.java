package com.codebear.demo.base;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/7.
 */

public class BaseResponse<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
