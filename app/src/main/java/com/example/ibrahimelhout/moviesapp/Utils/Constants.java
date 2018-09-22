package com.example.ibrahimelhout.moviesapp.Utils;

public interface Constants {

    String API_KEY  = "c1bb056c7ec1384a1029b0b0de3e6a8e";
    String API_BASE_URL = "http://api.themoviedb.org/3/";
    String POPULAR_MOVIES_LINK = "movie/popular?api_key=";
    String TOP_RATED_MOVIES_LINK = "movie/top_rated?api_key=";

    String PHOTO_BASE_URL = "https://image.tmdb.org/t/p/w500";


    String MOVIE_EXTRA_KEY = "movie_extra";
    String PAGE_QUERY = "page";
    int PAGE_KEY_REFRESH = 1;
    String RESULTS_OF_POPULAR_MOVIES_REQUEST = "results";
    String CURRENT_RECYCLER_LOCATION = "currentPositionOfRecycler";
    int TYPE_POPULAR = 111;
    int TYPE_TOP_RATED = 222;
}
