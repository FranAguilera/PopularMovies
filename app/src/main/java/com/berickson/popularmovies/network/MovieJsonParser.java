package com.berickson.popularmovies.network;


import com.berickson.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *  Parser for MovieDB JSON data.
 */
public class MovieJsonParser {

    /**
     * Parses a string containing MovideDB JSON data for a list of movies.
     *
     * @param movieJsonString raw JSON representing a list of movies
     * @return A list of movie objects
     * @throws JSONException
     */
    public static List<Movie> parseMovies(String movieJsonString) throws JSONException {

        List<Movie> movies = new ArrayList<>();

        JSONObject moviesJson = new JSONObject(movieJsonString);

        JSONArray moviesArray = moviesJson.getJSONArray("results");

        for (int i=0, size=moviesArray.length(); i<size; i++) {
            JSONObject movieJson = moviesArray.getJSONObject(i);
            Movie movie = parseMovie(movieJson);
            movies.add(movie);
        }
        return movies;
    }

    private static Movie parseMovie(JSONObject movieJson) throws JSONException {

        String id = movieJson.getString("id");
        String title = movieJson.getString("title");
        String releaseDate = movieJson.getString("release_date");
        String posterPath = movieJson.getString("poster_path");
        String voteAverage = movieJson.getString("vote_average");
        String overview = movieJson.getString("overview");

        return new Movie.Builder()
                .setId(id)
                .setTitle(title)
                .setReleaseDate(releaseDate)
                .setPosterUrl(posterPath)
                .setVoteAverage(voteAverage)
                .setPlotSynopsis(overview)
                .build();
    }
}
