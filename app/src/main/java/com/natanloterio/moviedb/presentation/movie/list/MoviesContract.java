package com.natanloterio.moviedb.presentation.movie.list;

import com.natanloterio.moviedb.data.genre.Genre;
import com.natanloterio.moviedb.data.movie.Movie;

import java.util.List;

/**
 * Created by natanloterio on 08/08/2016.
 */
public class MoviesContract {

    interface View{
        void showActivityIndicator();
        void hideActivityIndicator();
        void showPageLoad();
        void hidePageLoad();
        void showMovies(List<Movie> movies, int currentPage, int maxPage);
        void showError(Throwable e);
        void appendPage(List<Movie> movies, int currentPage);
        void showGenres(List<Genre> genres);
        void showNoMoviesFoundView();
    }

    public interface UserActionsListener{
        void loadMovies();
        void loadPage(int page);
        void loadGenres();
        void setView(MoviesContract.View view);
    }
}
