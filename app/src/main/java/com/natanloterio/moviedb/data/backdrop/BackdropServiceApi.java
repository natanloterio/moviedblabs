package com.natanloterio.moviedb.data.backdrop;

import java.util.List;

/**
 * Created by natanloterio on 27/11/16.
 */

public interface BackdropServiceApi {

    interface BackdropServiceApiCallback<T> {
        void onLoad(T load);

        void errorLoading(Throwable t);
    }

    void getBackdrops(int movieId, BackdropServiceApiCallback<List<Backdrop>> callback);
}
