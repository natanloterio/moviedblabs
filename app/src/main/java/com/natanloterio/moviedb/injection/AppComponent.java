package com.natanloterio.moviedb.injection;

import com.natanloterio.moviedb.presentation.movie.detail.MovieDetailActivity;
import com.natanloterio.moviedb.presentation.movie.list.MoviesFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by natanloterio on 05/12/16.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class, PresentationModule.class,
        RepositoryModule.class, NetModule.class})
public interface AppComponent {

    void inject(MovieDetailActivity activity);

    void inject(MoviesFragment moviesFragment);
}
