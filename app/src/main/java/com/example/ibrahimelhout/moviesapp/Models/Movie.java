package com.example.ibrahimelhout.moviesapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ibrahimelhout.moviesapp.Utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Parcelable {



    private int id;
    private String poster_path;
    private String backdrop_path;
    private double vote_average;
    private String overview;
    private String title;
    private String status;
    private String release_date;
    private int vote_count;

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return Constants.PHOTO_BASE_URL+poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return Constants.PHOTO_BASE_URL+backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }


    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
        dest.writeDouble(this.vote_average);
        dest.writeString(this.overview);
        dest.writeString(this.title);
        dest.writeString(this.status);
        dest.writeString(this.release_date);
        dest.writeInt(this.vote_count);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
        this.vote_average = in.readDouble();
        this.overview = in.readString();
        this.title = in.readString();
        this.status = in.readString();
        this.release_date = in.readString();
        this.vote_count = in.readInt();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
