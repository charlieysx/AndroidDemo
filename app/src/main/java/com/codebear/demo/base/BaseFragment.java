package com.codebear.demo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.codebear.demo.dialog.Loading;
import com.codebear.demo.utils.T;

import butterknife.ButterKnife;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusSupportFragment;
import rx.subscriptions.CompositeSubscription;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/6.
 */

public abstract class BaseFragment<P extends Presenter> extends NucleusSupportFragment<P> implements BaseView {

    protected Context mContext;
    protected View mRootView;
    private Loading loading;
    protected CompositeSubscription subscription = new CompositeSubscription();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        injectPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        afterCreate(savedInstanceState);
    }

    protected void injectPresenter() {
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading(boolean canCancelable) {
        loading = Loading.newInstance(canCancelable);
        loading.show(getFragmentManager(), "loading");
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
        T.s(mContext, errorMsg);
        dismissLoading();
    }
}
