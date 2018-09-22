package com.example.ibrahimelhout.moviesapp.Utils;

import com.example.ibrahimelhout.moviesapp.Models.Movie;
import com.example.ibrahimelhout.moviesapp.Models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyRetrofiInterface {


    @GET(Constants.POPULAR_MOVIES_LINK+Constants.API_KEY)
    Call<Result> getPupularMovies(@Query(Constants.PAGE_QUERY) String pageNumber);

    @GET(Constants.TOP_RATED_MOVIES_LINK+Constants.API_KEY)
    Call<Result> getTopRatedMovies(@Query(Constants.PAGE_QUERY) String pageNumber);

}
