package com.berickson.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.berickson.popularmovies.model.Movie;
import com.berickson.popularmovies.network.NetworkUtils;
import com.squareup.picasso.Picasso;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView thumbnail;
    private ThumbnailClickListenter thumbnailClickListenter;

    public MovieViewHolder(View itemView) {
        super(itemView);
        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        thumbnailClickListenter = new ThumbnailClickListenter();
        thumbnail.setOnClickListener(thumbnailClickListenter);
    }

    public void bind(@NonNull Movie movie) {
        Context context = itemView.getContext();
        String thumbnailURL = movie.getPosterURL();
        Uri imageUri = NetworkUtils.buildImageUri(thumbnailURL);
        Picasso.with(context)
                .load(imageUri)
                .into(thumbnail);

        thumbnailClickListenter.setMovie(movie);
    }

    private class ThumbnailClickListenter implements View.OnClickListener {

        private Movie movie;

        private void setMovie(Movie movie) {
            this.movie = movie;
        }

        @Override
        public void onClick(View view) {
            if (movie == null) return;
            MovieDetailsActivity.startMovieDetailsActivity(movie, view.getContext());
        }
    }
}
