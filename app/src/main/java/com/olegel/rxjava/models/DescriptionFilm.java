package com.olegel.rxjava.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DescriptionFilm implements Parcelable {

    private int id;
    @SerializedName("release_year")
    private String year;
    @SerializedName("rating")
    private String raiting;
    @SerializedName("summary")
    private String summary;
    @SerializedName("show_title")
    private String title;
    @SerializedName("category")
    private String category;
    @SerializedName("director")
    private String director;
    @SerializedName("poster")
    private String picture;
    @SerializedName("show_cast")
    private String actors;
    @SerializedName("runtime")
    private String runtime;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(year);
        parcel.writeString(raiting);
        parcel.writeString(title);
        parcel.writeString(category);
        parcel.writeString(director);
        parcel.writeString(picture);
        parcel.writeString(actors);
        parcel.writeString(runtime);
    }

    public DescriptionFilm() {

    }

    private DescriptionFilm(Parcel parcel) {
        year = parcel.readString();
        raiting = parcel.readString();
        title = parcel.readString();
        category = parcel.readString();
        director = parcel.readString();
        picture = parcel.readString();
        actors = parcel.readString();
        runtime = parcel.readString();
    }

    public static final Parcelable.Creator<DescriptionFilm> CREATOR =
            new Parcelable.Creator<DescriptionFilm>() {

                public DescriptionFilm createFromParcel(Parcel in) {
                    return new DescriptionFilm(in);
                }

                public DescriptionFilm[] newArray(int size) {
                    return new DescriptionFilm[size];
                }
            };


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRaiting() {
        return raiting;
    }

    public void setRaiting(String raiting) {
        this.raiting = raiting;
    }

    public String getSummary() {
        return summary;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
