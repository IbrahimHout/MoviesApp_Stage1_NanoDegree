package com.example.ibrahimelhout.moviesapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ibrahimelhout.moviesapp.Adapters.MoviesAdapter;
import com.example.ibrahimelhout.moviesapp.Models.Movie;
import com.example.ibrahimelhout.moviesapp.Models.Result;
import com.example.ibrahimelhout.moviesapp.Utils.Constants;
import com.example.ibrahimelhout.moviesapp.Utils.MyRetrofiInterface;
import com.example.ibrahimelhout.moviesapp.Utils.UtilsSinglton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private int sortType = Constants.TYPE_POPULAR;

    private static final String TAG = "MainActivity";
    @BindView(R.id.gridrecycler)

    RecyclerView gridRecycler;
    @BindView(R.id.refreshContainer)

    SwipeRefreshLayout refreshContainer;
    @BindView(R.id.progress_main)

    ProgressBar progressMain;


    private MyRetrofiInterface retrofiInterface;

    private MoviesAdapter moviesAdapter;
    private ArrayList<Movie> movies;
    private GridLayoutManager gridLayoutManager;

    int pageNumber = 0;
    @BindView(R.id.errorBG)
    ImageView errorBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initiateAdapterWithRecycler();
        initRequestsUtils();

        requestMovies(sortType);

        refreshContainer.setVisibility(View.GONE);
        refreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestMovies(sortType);
            }
        });
    }

    private void initRequestsUtils() {
        retrofiInterface = (UtilsSinglton.getRetrofitClient(Constants.API_BASE_URL)).create(MyRetrofiInterface.class);

    }

    private void initiateAdapterWithRecycler() {

        movies = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        moviesAdapter = new MoviesAdapter(this, movies);
        gridRecycler.setAdapter(moviesAdapter);
        gridRecycler.setLayoutManager(gridLayoutManager);
    }

    private void requestMovies(int opType) {


        Call<Result> getResults =null;
        //Edit op type todo**
        if (opType==Constants.TYPE_POPULAR){
             getResults = retrofiInterface.getPupularMovies("1");

        }else if (opType==Constants.TYPE_TOP_RATED)
        {

             getResults = retrofiInterface.getTopRatedMovies("1");
        }


        getResults.enqueue(new Callback<Result>() {


            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                Log.d(TAG, "onResponse: " + response.body().toString());


                ArrayList<Movie> newMovies = new ArrayList<>(response.body().getResults());
                refreshContainer.setRefreshing(false);

                populate(newMovies);


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d(TAG, "onFailure: " + call + "\n" + t);
                refreshContainer.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Network Error Happend", Toast.LENGTH_SHORT).show();

                errorBG.setVisibility(View.VISIBLE);
            }
        });

    }

    private void populate(ArrayList<Movie> newMovies) {

        movies.clear();
        movies.addAll(newMovies);
        moviesAdapter.notifyDataSetChanged();
        progressMain.setVisibility(View.GONE);
        errorBG.setVisibility(View.GONE);
        refreshContainer.setVisibility(View.VISIBLE);
        gridRecycler.smoothScrollToPosition(0);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_minue,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sortMovies:

                showSortPopUp(item);

                break;

                // Switch in case of adding other items to the menut
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSortPopUp(MenuItem item) {

        View anchorView = findViewById(R.id.sortMovies);
        PopupMenu popupMenu = new PopupMenu(this,anchorView);
        getMenuInflater().inflate(R.menu.sort_menu,  popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.mostPoplar:
                        sortType =Constants.TYPE_POPULAR;
                        break;
                    case R.id.topRated:
                        sortType =Constants.TYPE_TOP_RATED;
                        break;

                }

                changeSort();

                return false;
            }
        });

    }

    private void changeSort() {
        progressMain.setVisibility(View.VISIBLE);
        refreshContainer.setVisibility(View.GONE);
        requestMovies(sortType);
    }


}
