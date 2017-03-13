package com.olegel.rxjava.http;

import com.google.gson.JsonElement;
import com.olegel.rxjava.models.DescriptionFilm;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VideoRequest {
    @POST("/api/api.php?")
    Observable<DescriptionFilm> getFilmInformationForTitle(@Query(value = "title", encoded = true) String filmName);

    @POST("/api/api.php?")
    Call<JsonElement> getFilmInformationForDirector(@Query(value = "director", encoded = true) String director);

    @POST("/api/api.php?")
    Observable<DescriptionFilm> getFilmInformationForActor(@Query(value = "actor", encoded = true) String actor);
}


