package com.olegel.rxjava.http;

import android.util.Log;

import com.olegel.rxjava.models.DescriptionFilm;
import com.olegel.rxjava.util.ProjectConstants;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestForServer {
    private static final String TAG = RequestForServer.class.getSimpleName();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ProjectConstants.HOSTING_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public void request(){
        VideoRequest request = retrofit.create(VideoRequest.class);
        Observable<DescriptionFilm> filmObservable = request.getFilmInformationForTitle("Attack%20on%20titan");
        filmObservable.subscribeWith(new DisposableObserver<DescriptionFilm>() {
            @Override
            public void onNext(DescriptionFilm value) {
                Log.d(TAG, "request:1 "+value.getActors());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "request:2 "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "request:3 ");
            }
        });

    }



}
