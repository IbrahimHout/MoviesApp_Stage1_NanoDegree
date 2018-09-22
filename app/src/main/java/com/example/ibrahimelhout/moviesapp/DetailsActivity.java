package com.example.ibrahimelhout.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahimelhout.moviesapp.Models.Movie;
import com.example.ibrahimelhout.moviesapp.Utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        progressMovie.setVisibility(View.VISIBLE);

        if (movieLayout!=null)
        movieLayout.setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null) {
            movie =  intent.getParcelableExtra(Constants.MOVIE_EXTRA_KEY);


            setTitle(movie.getTitle());

            progressMovie.setVisibility(View.GONE);
            if (movieLayout!=null)
            movieLayout.setVisibility(View.VISIBLE);
            Picasso.get().load(movie.getBackdrop_path()).error(R.drawable.no_image).placeholder(R.drawable.no_image).into(movieImg);
            Picasso.get().load(movie.getPoster_path()).error(R.drawable.no_image).placeholder(R.drawable.no_image).into(moviePosterIV);
            movieTitle.setText(movie.getTitle());
            movieOverviewTV.setText(movie.getOverview());
            movieRate.setText(movie.getVote_average() + "");
            releaseDateTextView.setText(getString(R.string.release_date) + movie.getRelease_date());


        } else {

            Toast.makeText(this, "An error Happened, returning", Toast.LENGTH_SHORT).show();
        }


    }
}
