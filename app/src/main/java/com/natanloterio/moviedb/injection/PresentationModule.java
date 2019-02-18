package com.natanloterio.moviedb.injection;

import android.content.Context;

import com.natanloterio.moviedb.data.backdrop.BackdropRepository;
import com.natanloterio.moviedb.data.genre.GenresRepository;
import com.natanloterio.moviedb.data.movie.MoviesRepository;
import com.natanloterio.moviedb.data.video.VideosRepository;
import com.natanloterio.moviedb.presentation.movie.detail.MovieDetailContract;
import com.natanloterio.moviedb.presentation.movie.detail.MovieDetailPresenter;
import com.natanloterio.moviedb.presentation.movie.list.MoviesContract;
import com.natanloterio.moviedb.presentation.movie.list.MoviesPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TECBMCCS on 05/12/16.
 */


@Module
public class PresentationModule {

    @Provides
    MovieDetailContract.UserActionsListener providesMovieDetailLister(
            @Named("applicationContext") Context context,
            VideosRepository videosRepository,
            MoviesRepository moviesRepository,
            BackdropRepository backdropRepository) {

        return new MovieDetailPresenter(context, videosRepository, moviesRepository,
                backdropRepository);
    }

    @Provides
    MoviesContract.UserActionsListener providesMoviesActionListener(
            MoviesRepository moviesRepository, GenresRepository genresRepository) {

        return new MoviesPresenter(moviesRepository, genresRepository);
    }
}
