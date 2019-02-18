package com.natanloterio.moviedb.data.thumbnail;

import android.content.Context;
import android.widget.ImageView;

import com.natanloterio.moviedb.data.video.Video;

/**
 * Created by natanloterio on 25/11/16.
 */

public interface ThumbnailProvider {

    void loadThumbnail(ImageView target, Video video, Context context);
}
