package com.codebear.demo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import nucleus.presenter.RxPresenter;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/6.
 */

public class BasePresenter<V> extends RxPresenter<V> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void deliver(@NonNull Action1<? super V> onNext, @NonNull Action1<Throwable> onError) {
        view().filter(new Func1<V, Boolean>() {
            @Override
            public Boolean call(V v) {
                return v != null;
            }
        }).take(1).subscribe(onNext, onError);
    }

    protected void deliver(@NonNull Action1<? super V> onNext) {
        view().filter(new Func1<V, Boolean>() {
            @Override
            public Boolean call(V v) {
                return v != null;
            }
        }).take(1).subscribe(onNext);
    }
}
