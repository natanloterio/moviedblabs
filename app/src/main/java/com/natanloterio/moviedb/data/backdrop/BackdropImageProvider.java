package com.natanloterio.moviedb.data.backdrop;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by natanloterio on 27/11/16.
 */

public interface BackdropImageProvider {

    void load(ImageView target, Backdrop backdrop, Context context);
}
