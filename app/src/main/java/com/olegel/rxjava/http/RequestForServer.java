package com.olegel.rxjava.http;

import android.util.Log;

import com.olegel.rxjava.models.DescriptionFilm;
import com.olegel.rxjava.util.ProjectConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestForServer {
    private CompositeDisposable mCompositeDisposable;
    private static final String TAG = RequestForServer.class.getSimpleName();
    private List<DescriptionFilm> mAndroidArrayList;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ProjectConstants.HOSTING_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public void request(){
        VideoRequest request = retrofit.create(VideoRequest.class);
       /* Observable<DescriptionFilm> filmObservable = request.getFilmInformationForTitle("Attack%20on%20titan");
        filmObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<DescriptionFilm>() {
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
        });*/
      /*  Observable<ListFilms> filmsObservable = request.getFilmInformationForTitle("Attack%20on%20titan");
        mCompositeDisposable = new CompositeDisposable();
        filmsObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listFilms -> {Attack%20on%20titan
                    Log.e("Current Weather", listFilms.toString());Quentin%20Tarantino
                });*/
        //Observable<DescriptionFilm> desc = filmsObservable.concatMapIterable(this::handleResponse);
        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(request.getFilmInformationForActor("Quentin%20Tarantino")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(ListFilms<DescriptionFilm> listFilms) {
        mAndroidArrayList = new ArrayList<>();
        ListFilms bb = new ListFilms<>();
        Log.d(TAG, "handleResponse: "+listFilms.toString());
    }

    private void handleResponse(List androidList) {
        mAndroidArrayList = new ArrayList<>();
        mAndroidArrayList.addAll(androidList);

        Log.d(TAG, "handleResponse: "+mAndroidArrayList.get(0).getActors());
    }

    private void handleError(Throwable error) {

        Log.d(TAG, "handleError: "+error.getLocalizedMessage());
    }

    }




