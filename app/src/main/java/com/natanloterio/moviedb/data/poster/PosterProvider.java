package com.natanloterio.moviedb.data.poster;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by natanloterio on 24/11/16.
 */

public interface PosterProvider {

    void loadImage(String imagePath, ImageView target, Context context);

}
