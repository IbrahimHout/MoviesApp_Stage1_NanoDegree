package com.example.ibrahimelhout.moviesapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahimelhout.moviesapp.Adapters.ReviewsAdapter;
import com.example.ibrahimelhout.moviesapp.Adapters.TrailerAdapter;
import com.example.ibrahimelhout.moviesapp.Database.AddMovieViewModel;
import com.example.ibrahimelhout.moviesapp.Database.AddMovieViewModelFactory;
import com.example.ibrahimelhout.moviesapp.Database.AppExecutors;
import com.example.ibrahimelhout.moviesapp.Database.MyDB;
import com.example.ibrahimelhout.moviesapp.Models.Movie;
import com.example.ibrahimelhout.moviesapp.Models.MovieReview;
import com.example.ibrahimelhout.moviesapp.Models.MovieReviewResults;
import com.example.ibrahimelhout.moviesapp.Models.MovieTrailer;
import com.example.ibrahimelhout.moviesapp.Models.MovieVideos;
import com.example.ibrahimelhout.moviesapp.Network.MyRetrofiInterface;
import com.example.ibrahimelhout.moviesapp.Network.UtilsSinglton;
import com.example.ibrahimelhout.moviesapp.Utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class DetailsActivity extends AppCompatActivity {


    private static final String TAG = "DetailsActivity";
    Movie movie;
    @BindView(R.id.progress_movie)

    ProgressBar progressMovie;
    @BindView(R.id.movie_img)

    ImageView movieImg;
    @BindView(R.id.movie_title)

    TextView movieTitle;
    @BindView(R.id.movie_overviewTV)

    TextView movieOverviewTV;


    @BindView(R.id.movie_layout)
    LinearLayout movieLayout;

    @BindView(R.id.moviePosterIV)
    ImageView moviePosterIV;
    @BindView(R.id.movieRate)
    TextView movieRate;
    @BindView(R.id.releaseDateTextView)
    TextView releaseDateTextView;
    MyRetrofiInterface retrofiInterface;
    @BindView(R.id.addToFavTextView)
    TextView addToFavTextView;
    @BindView(R.id.addedToFavTextView)
    TextView addedToFavTextView;
    @BindView(R.id.trailerLabelTV)
    TextView trailerLabelTV;
    @BindView(R.id.trailersRecycler)
    RecyclerView trailersRecycler;
    @BindView(R.id.noTrailersTV)
    TextView noTrailersTV;
    @BindView(R.id.movieTrailersLayout)
    LinearLayout movieTrailersLayout;
    @BindView(R.id.reviewsLabelTV)
    TextView reviewsLabelTV;
    @BindView(R.id.reviewsRecycler)
    RecyclerView reviewsRecycler;
    @BindView(R.id.noReviewsTV)
    TextView noReviewsTV;
    @BindView(R.id.movieReviewssLayout)
    LinearLayout movieReviewssLayout;


    boolean isFav;

    MyDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);


        retrofiInterface = (UtilsSinglton.getRetrofitClient(Constants.API_BASE_URL)).create(MyRetrofiInterface.class);
        myDB = MyDB.getInstance(getApplicationContext());

        reviewsRecycler.setNestedScrollingEnabled(false);
        trailersRecycler.setNestedScrollingEnabled(false);

        progressMovie.setVisibility(View.VISIBLE);

        if (movieLayout != null)
            movieLayout.setVisibility(View.GONE);


        Intent intent = getIntent();
        if (intent != null) {
            movie = intent.getParcelableExtra(Constants.MOVIE_EXTRA_KEY);


            getIsFav( movie.getId());

            addToFavTextView.setVisibility(View.VISIBLE);


            setTitle(movie.getTitle());

            progressMovie.setVisibility(View.GONE);
            if (movieLayout != null)
                movieLayout.setVisibility(View.VISIBLE);
            Picasso.get().load(Constants.PHOTO_BASE_URL+movie.getBackdrop_path()).error(R.drawable.no_image).placeholder(R.drawable.no_image).into(movieImg);
            Picasso.get().load(Constants.PHOTO_BASE_URL+movie.getPoster_path()).error(R.drawable.no_image).placeholder(R.drawable.no_image).into(moviePosterIV);
            movieTitle.setText(movie.getTitle());
            movieOverviewTV.setText(movie.getOverview());
            movieRate.setText(movie.getVote_average() + "");
            releaseDateTextView.setText(getString(R.string.release_date) + movie.getRelease_date());

            getTrailers(movie.getId());
            getReviews(movie.getId());

        } else {

            Toast.makeText(this, "An error Happened, returning", Toast.LENGTH_SHORT).show();
        }


        addToFavTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (movie != null) {

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                                myDB.movieDAO().insertMovie(movie);

                        }
                    });

                    addToFavTextView.setVisibility(View.GONE);
                    addedToFavTextView.setVisibility(View.VISIBLE);

                }
                //Todo Add to favorite
            }
        });



        //todo delete
        addedToFavTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        myDB.movieDAO().deleteMovie(movie);


                    }
                });
                addToFavTextView.setVisibility(View.VISIBLE);
                addedToFavTextView.setVisibility(View.GONE);


            }
        });


    }

    private void getIsFav(final int id) {

        AddMovieViewModelFactory factory = new AddMovieViewModelFactory(myDB,id);


        final AddMovieViewModel viewModel
                = ViewModelProviders.of(this,factory).get(AddMovieViewModel.class);

        viewModel.getMovieLiveData().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                if (movie!=null)
                {
                    isFav = true;
                    addedToFavTextView.setVisibility(View.VISIBLE);
                    addToFavTextView.setVisibility(View.GONE);
                }else {
                    isFav = false;
                    addedToFavTextView.setVisibility(View.GONE);
                    addToFavTextView.setVisibility(View.VISIBLE);

                }
                viewModel.getMovieLiveData().removeObserver(this);
            }
        });

   }


    private void getReviews(int id) {


        Call<MovieReview> getReviews = retrofiInterface.getMoviesReviews(id + "");
        getReviews.enqueue(new Callback<MovieReview>() {
            @Override
            public void onResponse(Call<MovieReview> call, Response<MovieReview> response) {

                Log.d(TAG, "onResponse get Reviews: " + response.body().toString());

                ArrayList<MovieReviewResults> movieReviews = new ArrayList<>(response.body().getReviews());
                populateReviews(movieReviews);

            }

            @Override
            public void onFailure(Call<MovieReview> call, Throwable t) {
                populateReviews(null);

            }
        });


    }

    private void getTrailers(int id) {
        Call<MovieVideos> getMovieVideos = retrofiInterface.getMoviesVideos(id + "");
        getMovieVideos.enqueue(new Callback<MovieVideos>() {
            @Override
            public void onResponse(Call<MovieVideos> call, Response<MovieVideos> response) {
                ArrayList<MovieTrailer> trailers = new ArrayList<>(response.body().getTrailers());
                populateTrailers(trailers);
            }

            @Override
            public void onFailure(Call<MovieVideos> call, Throwable t) {
                populateTrailers(null);

            }
        });

    }


    private void populateReviews(ArrayList<MovieReviewResults> movieReviews) {
        if (movieReviews == null || movieReviews.size() == 0) {
            //fail
        } else {

            noReviewsTV.setVisibility(View.GONE);
            reviewsRecycler.setVisibility(View.VISIBLE);
            ReviewsAdapter adapter = new ReviewsAdapter(this, movieReviews);
            reviewsRecycler.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            reviewsRecycler.setLayoutManager(layoutManager);
            DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
            reviewsRecycler.addItemDecoration(decoration);
        }
    }

    private void populateTrailers(ArrayList<MovieTrailer> movieTrailers) {
        if (movieTrailers == null || movieTrailers.size() == 0) {
            //fail
        } else {
            noTrailersTV.setVisibility(View.GONE);
            trailersRecycler.setVisibility(View.VISIBLE);
            TrailerAdapter adapter = new TrailerAdapter(this, movieTrailers);
            trailersRecycler.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            trailersRecycler.setLayoutManager(layoutManager);

            DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
            trailersRecycler.addItemDecoration(decoration);
        }
    }





}
