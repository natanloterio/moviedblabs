package com.natanloterio.moviedb.data.video;

import java.util.List;

/**
 * Created by natanloterio on 25/11/16.
 */

public interface VideosRepository {

    interface LoadVideosCallback {
        void onLoadVideos(List<Video> videos);

        void errorLoadingVideos(Throwable e);
    }

    void getMovies(int movieId, LoadVideosCallback callback);
}