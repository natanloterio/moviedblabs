package com.natanloterio.moviedb.data.backdrop;

import java.util.List;

/**
 * Created by natanloterio on 27/11/16.
 */

public interface BackdropRepository {

    interface LoadBackdropCallback {
        void onLoadBackdrops(List<Backdrop> backdrops);

        void errorLoadingBackdrops(Throwable t);
    }

    void getBackdrops(int movieId, LoadBackdropCallback callback);
}
