package com.natanloterio.moviedb.data.genre;

import java.util.List;

/**
 * Created by natanloterio on 03/08/2016.
 */
public interface GenresServiceApi {

    interface GenresServiceCallback<T> {
        void onLoaded(T load);

        void onError(Throwable t);
    }

    void getGenres(GenresServiceCallback<List<Genre>> callback);

}
