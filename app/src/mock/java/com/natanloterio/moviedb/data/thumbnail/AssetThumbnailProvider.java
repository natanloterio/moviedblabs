package com.natanloterio.moviedb.data.thumbnail;

import android.content.Context;
import android.widget.ImageView;

import com.natanloterio.moviedb.data.video.Video;
import com.squareup.picasso.Picasso;

/**
 * Created by natanloterio on 25/11/16.
 */
public class AssetThumbnailProvider implements ThumbnailProvider {
    @Override
    public void loadThumbnail(ImageView target, Video video, Context context) {
        Picasso.with(context)
                .load("file:///android_asset/poster_test.jpg")
                .into(target);
    }
}
