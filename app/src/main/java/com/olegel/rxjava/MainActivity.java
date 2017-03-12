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
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {
private TextView textView;
    private Observer<Long> subscribe;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        final Scheduler sch = new Scheduler() {
            @Override
            public Worker createWorker() {
                Log.d(TAG, "createWorker: ");
                return null;
            }
        };
       Observable<Long> sub1 = Observable.interval(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());
                sub1.subscribeOn(sch);
           
       
       sub1.unsubscribeOn(sch);

    }
    private String setTextView(){
        textView.setText(Long.toString(System.currentTimeMillis()));
          return ""; }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
