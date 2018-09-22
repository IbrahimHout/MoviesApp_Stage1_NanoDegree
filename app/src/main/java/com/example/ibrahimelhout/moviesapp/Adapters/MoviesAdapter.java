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
import android.widget.TextView;

import com.example.ibrahimelhout.moviesapp.DetailsActivity;
import com.example.ibrahimelhout.moviesapp.Models.Movie;
import com.example.ibrahimelhout.moviesapp.R;
import com.example.ibrahimelhout.moviesapp.Utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MobviePlaceholder> {


    private static final String TAG = "MoviesAdapter";
    private Context context;
    private ArrayList<Movie> movies;

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    public MobviePlaceholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_movie, viewGroup, false);

        MobviePlaceholder mobviePlaceholder = new MobviePlaceholder(view);
        return mobviePlaceholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MobviePlaceholder mobviePlaceholder, final int i) {
        mobviePlaceholder.movieTitleTV.setText(movies.get(i).getTitle());
        mobviePlaceholder.movieRateBadgeTV.setText(movies.get(i).getVote_average()+"");

        Picasso.get().load(movies.get(i).getPoster_path()).placeholder(R.drawable.no_image).error(R.drawable.no_image).into(mobviePlaceholder.moviePosterIV);

        Log.d(TAG, "onBindViewHolder: "+movies.get(i).getPoster_path());
        Log.d(TAG, "onBindViewHolder: ");
        mobviePlaceholder.moviePosterIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(Constants.MOVIE_EXTRA_KEY,movies.get(i));
                intent.putExtra("test","test");
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MobviePlaceholder extends RecyclerView.ViewHolder {

        @BindView(R.id.moviePoster)
        ImageView moviePosterIV;
        @BindView(R.id.moveTitle)
        TextView movieTitleTV;
        @BindView(R.id.movieRateBadge)
        TextView movieRateBadgeTV;

        MobviePlaceholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
