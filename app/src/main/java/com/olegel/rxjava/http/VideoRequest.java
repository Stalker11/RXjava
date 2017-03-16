package com.olegel.rxjava.http;

import com.olegel.rxjava.models.DescriptionFilm;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VideoRequest {
    @POST("/api/api.php?")
    Observable<List<DescriptionFilm>> getFilmInformationForTitle(@Query(value = "title", encoded = true) String filmName);

    @POST("/api/api.php?")
    Observable<ListFilms> getFilmInformationForDirector(@Query(value = "director", encoded = true) String director);

    @POST("/api/api.php?")
    Observable<List<DescriptionFilm>> getFilmInformationForActor(@Query(value = "actor", encoded = true) String actor);
}


