package com.berickson.popularmovies;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.berickson.popularmovies.model.Movie;
import com.berickson.popularmovies.network.ImageUtils;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String MOVIE_KEY = "movie";

    private TextView movieTitle;
    private ImageView moviePoster;
    private TextView releaseDate;
    private TextView userRating;
    private TextView plotSynopsis;

    private Movie movie;

    public static void startMovieDetailsActivity(Movie movie, Context context) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MOVIE_KEY, movie);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        initializeViews();

        Intent intent = getIntent();
        if (intent == null) return;

        if (intent.hasExtra(MOVIE_KEY)) {
            movie = (Movie) intent.getSerializableExtra(MOVIE_KEY);
            populateMovieDetails();
        } else {
            finish();
        }
    }

    private void initializeViews() {
        movieTitle = (TextView) findViewById(R.id.movie_title);
        moviePoster = (ImageView) findViewById(R.id.movie_thumbnail);
        releaseDate = (TextView) findViewById(R.id.release_date);
        userRating = (TextView) findViewById(R.id.user_rating);
        plotSynopsis = (TextView) findViewById(R.id.plot_synopsis);
    }

    private void populateMovieDetails() {
        movieTitle.setText(movie.getTitle());
        ImageUtils.loadMovieImage(this, movie.getPosterURL(), moviePoster);
        releaseDate.setText(movie.getReleaseDate());
        userRating.setText(movie.getVoteAverage());
        plotSynopsis.setText(movie.getPlotSynopsis());
    }
}
