package com.example.ibrahimelhout.moviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ibrahimelhout.moviesapp.DetailsActivity;
import com.example.ibrahimelhout.moviesapp.FavoriteMoviesActivity;
import com.example.ibrahimelhout.moviesapp.Models.Movie;
import com.example.ibrahimelhout.moviesapp.R;
import com.example.ibrahimelhout.moviesapp.Utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavViewHHolder> {

    Context context;
    ArrayList<Movie> movies;


    public ArrayList<Movie> getMovies() {
        return movies;
    }


    public FavoritesAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public FavViewHHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_fav_recylcer, null, false);


        return new FavViewHHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavViewHHolder favViewHHolder, int i) {


        favViewHHolder.movieName.setText(movies.get(i).getTitle());
        Log.d("test", "onBindViewHolder: "+ movies.get(i).getPoster_path());

        Picasso.get().load(Constants.PHOTO_BASE_URL+movies.get(i).getPoster_path()).placeholder(R.drawable.no_image).error(R.drawable.no_image).into(favViewHHolder.moviePosterIVFav);

        favViewHHolder.cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(Constants.MOVIE_EXTRA_KEY,movies.get(favViewHHolder.getAdapterPosition()));

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }





    class FavViewHHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.moviePosterIVFav)
        ImageView moviePosterIVFav;
        @BindView(R.id.movie_name)
        TextView movieName;
        @BindView(R.id.cell)
        LinearLayout cell;

        public FavViewHHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

}
