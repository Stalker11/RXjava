package com.olegel.rxjava;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.widget.TextView;

import com.olegel.rxjava.http.RequestForServer;
import com.olegel.rxjava.recivers.NetworkReciver;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView internet;
    private Observer<Long> subscribe;
    private Disposable disp;
    public static final String TAG = MainActivity.class.getSimpleName();
    private NetworkReciver receiver = new NetworkReciver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        internet = (TextView) findViewById(R.id.textView);
        new RequestForServer().request();
        internetConnection();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReciver();
        this.registerReceiver(receiver, filter);
        Log.d(TAG, "onCreate: "+WiFiInspection());
        disp = Observable.interval(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "onNext:1 " + setTextView());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onNext:2 " + setTextView());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onNext:3 " + setTextView());
                    }
                });
    }

    private String setTextView() {
        textView.setText(Long.toString(System.currentTimeMillis()));
        return "";
    }

    @Override
    protected void onPostResume() {
        if(receiver != null){
            internet.setText(receiver.networkState());
        }
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        disp.dispose();
        if (receiver != null) {
            this.unregisterReceiver(receiver);
        }
        super.onDestroy();
    }
    private boolean WiFiInspection() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
///
        NetworkInfo tt = connManager.getActiveNetworkInfo();

        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo ethernet = connManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        if (mobile.isConnected()) {
            return true;
        }
        return false;
    }
    public void internetConnection() {
        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

//mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState();

//wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState();


        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
            Log.d(TAG, "internetConnection:1 "+mobile);
        } else if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
            Log.d(TAG, "internetConnection:2 "+wifi);
        }
    }
}
