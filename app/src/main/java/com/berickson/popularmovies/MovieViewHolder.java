package com.berickson.popularmovies;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.berickson.popularmovies.model.Movie;
import com.berickson.popularmovies.network.ImageUtils;
import com.berickson.popularmovies.network.NetworkUtils;
import com.squareup.picasso.Picasso;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView thumbnail;

    public MovieViewHolder(View itemView) {
        super(itemView);

        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
    }

    public void bind(@NonNull Movie movie) {
        Context context = itemView.getContext();
        String thumbnailURL = movie.getPosterURL();
        Uri imageUri = NetworkUtils.buildImageUri(thumbnailURL);
        Picasso.with(context)
                .load(imageUri)
                .into(thumbnail);
    }
}
