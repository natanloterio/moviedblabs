package com.natanloterio.moviedb.data.thumbnail;

import android.content.Context;
import android.widget.ImageView;

import com.natanloterio.moviedb.data.thumbnail.ThumbnailProvider;
import com.natanloterio.moviedb.data.video.Video;
import com.squareup.picasso.Picasso;

/**
 * Created by natanloterio on 25/11/16.
 */

public class YoutubeThumbnailProvider implements ThumbnailProvider {
    private String baseYoutubeUrl = "https://img.youtube.com/vi/%s/1.jpg";

    @Override
    public void loadThumbnail(ImageView target, Video video, Context context) {
        Picasso.with(context)
                .load(String.format(baseYoutubeUrl, video.getKey()))
                .into(target);
    }
}
