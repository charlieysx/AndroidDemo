package com.codebear.demo.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.codebear.demo.R;
import com.codebear.demo.dialog.Loading;
import com.codebear.demo.utils.T;

import butterknife.ButterKnife;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusAppCompatActivity;
import rx.subscriptions.CompositeSubscription;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/6.
 */

public abstract class BaseActivity<P extends Presenter> extends NucleusAppCompatActivity<P> implements BaseView {

    private Loading loading;
    protected CompositeSubscription subscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectPresenter();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        afterCreate(savedInstanceState);
    }

    protected void injectPresenter() {
    }

    /**
     * 默认使用这个布局，需要用别的布局的就要重写这个方法
     *
     * @return
     */
    protected int getLayoutId() {
        return R.layout.activity_common;
    }

    protected abstract void afterCreate(Bundle savedInstanceState);

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void showLoading(boolean canCancelable) {
        loading = Loading.newInstance(canCancelable);
        loading.show(getSupportFragmentManager(), "loading");
    }

    @Override
    public void dismissLoading() {
        if (loading != null) {
            loading.dismiss();
        }
    }

    @Override
    public void showSuccessMsg(String successMsg, Object o) {
        dismissLoading();
    }

    @Override
    public void showErrorMsg(String errorMsg, Object o) {
        T.s(this, errorMsg);
        dismissLoading();
    }
}
