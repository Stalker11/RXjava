package com.olegel.rxjava.http;

import com.olegel.rxjava.models.DescriptionFilm;

import java.util.List;

public class ListFilms<D> {
    private List<DescriptionFilm> films;

    public List<DescriptionFilm> getFilms() {
        return films;
    }

    public void setFilms(List<DescriptionFilm> films) {
        this.films = films;
    }
}
