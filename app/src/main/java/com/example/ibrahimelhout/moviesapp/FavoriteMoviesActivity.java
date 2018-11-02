package com.example.ibrahimelhout.moviesapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahimelhout.moviesapp.Adapters.FavoritesAdapter;
import com.example.ibrahimelhout.moviesapp.Database.MoviesViewModel;
import com.example.ibrahimelhout.moviesapp.Database.MyDB;
import com.example.ibrahimelhout.moviesapp.Models.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class FavoriteMoviesActivity extends AppCompatActivity {

    @BindView(R.id.noFavTV)
    TextView noFavTV;
    @BindView(R.id.favRecyclerView)
    RecyclerView favRecyclerView;
    @BindView(R.id.favProgress)
    ProgressBar favProgress;
    FavoritesAdapter adapter;



    MyDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        ButterKnife.bind(this);
        setTitle(getString(R.string.favorites));

        initiateRecycler();


        myDB =MyDB.getInstance(getApplicationContext());
        setupViewModel();


    }

    private void initiateRecycler() {
        ArrayList<Movie> movies = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        favRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new FavoritesAdapter(this, movies);
        favRecyclerView.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        favRecyclerView.addItemDecoration(decoration);


    }


    public void populateFavScreen(ArrayList<Movie> newMovies) {

        Toast.makeText(this, "num : "+newMovies.size(), Toast.LENGTH_SHORT).show();
        favProgress.setVisibility(View.GONE);


        if (newMovies.size() == 0) {
            noFavTV.setVisibility(View.VISIBLE);
            favRecyclerView.setVisibility(View.GONE);
        } else {


            noFavTV.setVisibility(View.GONE);
            favRecyclerView.setVisibility(View.VISIBLE);
            adapter.getMovies().clear();
            adapter.getMovies().addAll(newMovies);
            adapter.notifyDataSetChanged();

        }


    }

    private void setupViewModel() {
        MoviesViewModel viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                populateFavScreen((ArrayList<Movie>) movies);
            }

        });
    }



}
