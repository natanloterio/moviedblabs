package com.natanloterio.moviedb.data.poster;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by natanloterio on 24/11/16.
 */

public class AssetPosterProvider implements PosterProvider{
    @Override
    public void loadImage(String imagePath, ImageView target, Context context) {
        Picasso.with(context)
                .load(imagePath)
                .into(target);
    }
}
