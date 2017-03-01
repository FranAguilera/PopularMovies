package com.berickson.popularmovies.network;


import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 *  Facade for Image Loading APIs
 */
public class ImageUtils {

    public static void loadMovieImage(Context context, String filePath, ImageView destinationView) {
        Uri imageUri = NetworkUtils.buildImageUri(filePath);
        Picasso.with(context)
                .load(imageUri)
                .into(destinationView);
    }
}
