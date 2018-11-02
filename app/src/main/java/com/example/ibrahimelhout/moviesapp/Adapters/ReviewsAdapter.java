package com.example.ibrahimelhout.moviesapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibrahimelhout.moviesapp.Models.MovieReviewResults;
import com.example.ibrahimelhout.moviesapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    Context context;
    ArrayList<MovieReviewResults> movieReviews;

    public ReviewsAdapter(Context context, ArrayList<MovieReviewResults> movieReviews) {
        this.context = context;
        this.movieReviews = movieReviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_review, null, false);

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int i) {

        reviewViewHolder.authorTV.setText(movieReviews.get(i).getAuthor());
        reviewViewHolder.contentTV.setText(movieReviews.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return movieReviews.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.authorTV)
        TextView authorTV;
        @BindView(R.id.contentTV)
        TextView contentTV;
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }


}
