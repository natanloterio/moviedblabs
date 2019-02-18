package com.natanloterio.moviedb.data.poster;

import android.content.Context;
import android.widget.ImageView;

import com.natanloterio.moviedb.data.poster.PosterProvider;
import com.squareup.picasso.Picasso;

/**
 * Created by natanloterio on 24/11/16.
 */

public class PosterProviderImpl implements PosterProvider {

    public static final String BASE_PORSTER_URL = "http://image.tmdb.org/t/p/w500";

    @Override
    public void loadImage(String posterPath, ImageView target, Context context) {
        Picasso.with(context)
                .load(PosterProviderImpl.BASE_PORSTER_URL + posterPath)
                .into(target);
    }

}
