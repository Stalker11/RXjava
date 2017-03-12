package com.olegel.rxjava;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Obs implements Observer<Long>, Consumer<Long> {
    private static final String TAG = Obs.class.getSimpleName();
    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe:1 "+d.isDisposed());

    }

    @Override
    public void onNext(Long value) {
        Log.d(TAG, "onSubscribe:2 ");
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onSubscribe:3 ");
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onSubscribe:4 ");
    }

    @Override
    public void accept(Long aLong) throws Exception {

    }
}
