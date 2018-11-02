package com.example.ibrahimelhout.moviesapp.Database;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.ibrahimelhout.moviesapp.Models.Movie;

public class AddMovieViewModel extends ViewModel {

    private LiveData<Movie> movieLiveData;

    public AddMovieViewModel(MyDB database, int movieID) {

        movieLiveData = database.movieDAO().loadMovieById(movieID);

    }

    // COMPLETED (7) Create a getter for the task variable
    public LiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }
}

