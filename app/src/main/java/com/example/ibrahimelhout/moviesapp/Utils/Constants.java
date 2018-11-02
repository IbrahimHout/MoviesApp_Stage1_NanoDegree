package com.example.ibrahimelhout.moviesapp.Utils;

import com.example.ibrahimelhout.moviesapp.BuildConfig;

public interface Constants {

    String API_KEY  = BuildConfig.ApiKey;
    String API_BASE_URL = "http://api.themoviedb.org/3/";
    String POPULAR_MOVIES_LINK = "movie/popular?api_key=";
    String TOP_RATED_MOVIES_LINK = "movie/top_rated?api_key=";

    String BASE_YOUTUBE_URL = "http://www.youtube.com/watch?v=";


    String PHOTO_BASE_URL = "https://image.tmdb.org/t/p/w500";


    String MOVIE_EXTRA_KEY = "movie_extra";
    String PAGE_QUERY = "page";
    int PAGE_KEY_REFRESH = 1;
    String RESULTS_OF_POPULAR_MOVIES_REQUEST = "results";
    String CURRENT_RECYCLER_LOCATION = "currentPositionOfRecycler";
    int TYPE_POPULAR = 111;
    int TYPE_TOP_RATED = 222;
    int TYPE_FAV = 333;
    String LOCATION_OF_LINEAR_LAYOUT = "locationOfLinearLayout";
    String SORT_TYPE = "sortType";
}
