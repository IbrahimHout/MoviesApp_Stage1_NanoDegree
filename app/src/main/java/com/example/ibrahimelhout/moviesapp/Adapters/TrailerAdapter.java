package com.example.ibrahimelhout.moviesapp.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ibrahimelhout.moviesapp.Models.MovieTrailer;
import com.example.ibrahimelhout.moviesapp.R;
import com.example.ibrahimelhout.moviesapp.Utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>{

    private Context context;
    private ArrayList<MovieTrailer> trailers;

    public TrailerAdapter(Context context, ArrayList<MovieTrailer> trailers) {
        this.context = context;
        this.trailers = trailers;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context ).inflate(R.layout.cell_trailer,null,false);


        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder trailerViewHolder, final int i) {

        trailerViewHolder.name.setText(trailers.get(i).getName());
        trailerViewHolder.cellTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MovieTrailer trailer = trailers.get(i);
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer.getKey()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(Constants.BASE_YOUTUBE_URL + trailer.getKey()));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }


    class TrailerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cellTrailer)
        LinearLayout cellTrailer;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
