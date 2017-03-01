package com.berickson.popularmovies.network;


import android.net.Uri;
import android.support.annotation.StringDef;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({POPULAR_FILTER, RATING_FILTER})
    public @interface FilterType {}
    public static final String POPULAR_FILTER =  "popular";
    public static final String RATING_FILTER =  "top_rated";

    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";
    private static final String BASE_MOVIE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String IMAGE_SIZE = "w185";

    private static final String API_KEY_QUERY = "api_key";
    //TODO: put Movie DB API key here
    private static final String API_KEY = "";

    /**
     * Builds the URL used to talk to the movie db server using a filter.
     */
    public static URL buildMovieListUrl(@FilterType String filterType) {
        Uri builtUri = Uri.parse(BASE_MOVIE_URL).buildUpon()
                .appendEncodedPath(filterType)
                .appendQueryParameter(API_KEY_QUERY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static Uri buildImageUri(String filePath) {
        return Uri.parse(BASE_IMAGE_URL)
                .buildUpon()
                .appendEncodedPath(IMAGE_SIZE)
                .appendEncodedPath(filePath)
                .build();
    }

    /**
     * CopyPasta From Udacity coursework examples!!!
     *
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
