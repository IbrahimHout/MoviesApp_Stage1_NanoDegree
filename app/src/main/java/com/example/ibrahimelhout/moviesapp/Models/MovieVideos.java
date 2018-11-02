package com.example.ibrahimelhout.moviesapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideos {

    int id;

    @SerializedName("results")
    List<MovieTrailer> trailers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieTrailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<MovieTrailer> trailers) {
        this.trailers = trailers;
    }
}
