package com.example.ibrahimelhout.moviesapp.Network;

import com.example.ibrahimelhout.moviesapp.Models.MovieReview;
import com.example.ibrahimelhout.moviesapp.Models.MovieVideos;
import com.example.ibrahimelhout.moviesapp.Models.Result;
import com.example.ibrahimelhout.moviesapp.Utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyRetrofiInterface {


    @GET(Constants.POPULAR_MOVIES_LINK+Constants.API_KEY)
    Call<Result> getPupularMovies(@Query(Constants.PAGE_QUERY) String pageNumber);

    @GET(Constants.TOP_RATED_MOVIES_LINK+Constants.API_KEY)
    Call<Result> getTopRatedMovies(@Query(Constants.PAGE_QUERY) String pageNumber);

    @GET("movie/{id}/videos?api_key=" + Constants.API_KEY )
    Call<MovieVideos> getMoviesVideos(@Path("id") String movieId);

    @GET("movie/{id}/reviews?api_key=" + Constants.API_KEY)
    Call<MovieReview> getMoviesReviews(@Path("id") String movieId);

}
