package com.olegel.rxjava.http;

import com.olegel.rxjava.models.DescriptionFilm;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VideoRequest {
    @POST("/?json=1")
    Observable<DescriptionFilm> getFilmInformationForTitle();

    @POST("/api/api.php?")
    Observable<ListFilms> getFilmInformationForDirector(@Query(value = "director", encoded = true) String director);

    @POST("/?json=2")
    Observable<List<DescriptionFilm>> getFilmInformationForActor();
}


