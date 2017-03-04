package com.berickson.popularmovies.network;


import android.os.AsyncTask;

import com.berickson.popularmovies.model.Movie;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

public class LoadMovieDataTask extends AsyncTask<String, Void, List<Movie>> {

    public interface LoadMovieDataTaskCallback {
        void onMovieLoadComplete(List<Movie> movieList);
        void onMovieLoadFailed();
    }

    private WeakReference<LoadMovieDataTaskCallback> callbackReference;

    public LoadMovieDataTask(LoadMovieDataTaskCallback callback) {
        callbackReference = new WeakReference<>(callback);
    }

    @Override
    protected List<Movie> doInBackground(@NetworkUtils.FilterType String... params) {

        @NetworkUtils.FilterType String filterType = params[0];
        URL movieRequestUrl = NetworkUtils.buildMovieListUrl(filterType);

        try {
            String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
            return MovieJsonParser.parseMovies(jsonMovieResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movieList) {
        LoadMovieDataTaskCallback callback = callbackReference.get();
        if (callback == null) return;

        if (movieList == null) {
            callback.onMovieLoadFailed();
            return;
        }

        callback.onMovieLoadComplete(movieList);
    }
}
