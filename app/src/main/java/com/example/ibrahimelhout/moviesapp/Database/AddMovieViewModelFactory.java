package com.example.ibrahimelhout.moviesapp.Database;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class AddMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private  final MyDB myDB;
    private  final int movieID;

    public AddMovieViewModelFactory (MyDB database,int id){
        myDB = database;
        movieID =id;

    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddMovieViewModel(myDB, movieID);
    }

}
