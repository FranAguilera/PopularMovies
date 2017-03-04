package com.berickson.popularmovies;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.berickson.popularmovies.model.Movie;
import com.berickson.popularmovies.network.LoadMovieDataTask;
import com.berickson.popularmovies.network.MovieJsonParser;
import com.berickson.popularmovies.network.NetworkUtils;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoadMovieDataTask.LoadMovieDataTaskCallback {

    private static final int LIST_COLUMNS = 2;
    private static final int LIST_COLUMNS_LANDSCAPE = 3;

    private MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView movieList = (RecyclerView) findViewById(R.id.movie_list);
        movieListAdapter = new MovieListAdapter();
        movieList.setAdapter(movieListAdapter);
        GridLayoutManager gridlayoutManager = initializeGridLayout();
        movieList.setLayoutManager(gridlayoutManager);
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

    @Override
    public void onMovieLoadComplete(List<Movie> movieList) {
        movieListAdapter.replaceAll(movieList);
    }

    @Override
    public void onMovieLoadFailed() {
        Toast.makeText(this, R.string.movie_load_error, Toast.LENGTH_SHORT).show();
    }

    private GridLayoutManager initializeGridLayout() {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            return new GridLayoutManager(this, LIST_COLUMNS);
        } else {
            return new GridLayoutManager(this, LIST_COLUMNS_LANDSCAPE);
        }
    }

    private void loadMovieData(@NetworkUtils.FilterType String filterType) {
        new LoadMovieDataTask(this).execute(filterType);
    }
}
