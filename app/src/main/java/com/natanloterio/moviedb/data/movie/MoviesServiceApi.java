package com.natanloterio.moviedb.data.movie;

import java.util.List;

/**
 * Created by natanloterio on 08/08/2016.
 */
public interface MoviesServiceApi {

    interface MoviesServiceCallback<T> {
        void onLoaded(T load, int currentPage, int maxPage);

        void onError(Throwable e);
    }

    interface MovieServiceCallback {
        void onLoadMovie(Movie movie);

        void errorLoadingMovie(Throwable t);
    }

    void getMovies(int page, MoviesServiceCallback<List<Movie>> callback);

    void getMovie(int movieId, MovieServiceCallback callback);
}