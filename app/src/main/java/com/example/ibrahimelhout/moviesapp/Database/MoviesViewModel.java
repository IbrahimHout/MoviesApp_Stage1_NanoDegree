package com.example.ibrahimelhout.moviesapp.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.ibrahimelhout.moviesapp.Models.Movie;

import java.util.List;

public class MoviesViewModel extends AndroidViewModel {

    private LiveData<List<Movie>> movies;

    public MoviesViewModel(@NonNull Application application) {
        super(application);

        MyDB moviesDatabase = MyDB.getInstance(this.getApplication());
        movies = moviesDatabase.movieDAO().loadAllMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }



}
