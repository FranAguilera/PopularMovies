package com.berickson.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.berickson.popularmovies.model.Movie;
import com.berickson.popularmovies.network.MovieJsonParser;
import com.berickson.popularmovies.network.NetworkUtils;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int LIST_COLUMNS = 2;

    private MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView movieList = (RecyclerView) findViewById(R.id.movie_list);
        movieListAdapter = new MovieListAdapter();
        movieList.setAdapter(movieListAdapter);
        movieList.setLayoutManager(new GridLayoutManager(this, LIST_COLUMNS));
        loadMovieData(NetworkUtils.POPULAR_FILTER);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_filter_popular:
                loadMovieData(NetworkUtils.POPULAR_FILTER);
                break;
            case R.id.action_filter_rating:
                loadMovieData(NetworkUtils.RATING_FILTER);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadMovieData(@NetworkUtils.FilterType String filterType) {
        new LoadMovieDataTask().execute(filterType);
    }

    private class LoadMovieDataTask extends AsyncTask<String, Void, List<Movie>> {

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
            if (movieList == null) return;

            movieListAdapter.replaceAll(movieList);
        }
    }
}
