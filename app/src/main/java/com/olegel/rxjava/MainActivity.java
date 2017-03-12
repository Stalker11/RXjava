package com.olegel.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {
private TextView textView;
    private Observer<Long> subscribe;
    private Disposable disp;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);

      disp = Observable.interval(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "onNext:1 "+setTextView());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onNext:2 "+setTextView());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onNext:3 "+setTextView());
                    }
                });

    }
    private String setTextView(){
        textView.setText(Long.toString(System.currentTimeMillis()));
          return ""; }

    @Override
    protected void onDestroy() {
        disp.dispose();
        super.onDestroy();
    }
}
