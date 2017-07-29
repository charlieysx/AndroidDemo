package com.codebear.demo.base;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/6.
 */

public interface BaseView {
    /**
     * 显示加载弹出框
     */
    void showLoading(boolean canCancelable);

    /**
     * 关闭加载弹出框
     */
    void dismissLoading();

    /**
     * 显示成功信息
     *
     * @param successMsg
     * @param o
     */
    void showSuccessMsg(String successMsg, Object o);

    /**
     * 显示错误信息
     *
     * @param errorMsg
     * @param o
     */
    void showErrorMsg(String errorMsg, Object o);
}
