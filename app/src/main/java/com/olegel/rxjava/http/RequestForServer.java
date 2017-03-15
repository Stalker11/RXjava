package com.olegel.rxjava.http;

import android.util.Log;
import android.widget.Toast;

import com.olegel.rxjava.models.DescriptionFilm;
import com.olegel.rxjava.util.ProjectConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
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
        Observable<DescriptionFilm> filmObservable = request.getFilmInformationForTitle("Attack%20on%20titan");
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
        });
        Observable<ListFilms> filmsObservable = request.getFilmInformationForActor("Quentin%20Tarantino");
        mCompositeDisposable = new CompositeDisposable();

        //Observable<DescriptionFilm> desc = filmsObservable.concatMapIterable(this::handleResponse);
        mCompositeDisposable.add(request.getFilmInformationForActor("Quentin%20Tarantino")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));

    }

    private void handleResponse(ListFilms listFilms) {
        mAndroidArrayList = new ArrayList<DescriptionFilm>((Collection<? extends DescriptionFilm>) listFilms);
        Log.d(TAG, "handleResponse: "+mAndroidArrayList.get(0).getActors());
    }

    private void handleResponse(List<DescriptionFilm> androidList) {


    }

    private void handleError(Throwable error) {

        Log.d(TAG, "handleError: "+error.getLocalizedMessage());
    }


    }




