package com.natanloterio.moviedb.injection;

import com.natanloterio.moviedb.data.backdrop.BackdropRepository;
import com.natanloterio.moviedb.data.backdrop.BackdropRepositoryImpl;
import com.natanloterio.moviedb.data.backdrop.BackdropServiceApi;
import com.natanloterio.moviedb.data.genre.GenresRepository;
import com.natanloterio.moviedb.data.genre.GenresRepositoryImpl;
import com.natanloterio.moviedb.data.genre.GenresServiceApi;
import com.natanloterio.moviedb.data.movie.MoviesRepository;
import com.natanloterio.moviedb.data.movie.MoviesRepositoryImpl;
import com.natanloterio.moviedb.data.movie.MoviesServiceApi;
import com.natanloterio.moviedb.data.video.VideoServiceApi;
import com.natanloterio.moviedb.data.video.VideosRepository;
import com.natanloterio.moviedb.data.video.VideosRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by natanloterio on 05/12/16.
 */

@Module(includes = DataModule.class)
public class RepositoryModule {

    @Provides
    @Singleton
    BackdropRepository providesBackdropRepository(BackdropServiceApi backdropServiceApi) {
        return new BackdropRepositoryImpl(backdropServiceApi);
    }

    @Provides
    @Singleton
    GenresRepository providesGenresRepository(GenresServiceApi genreServiceApi) {
        return new GenresRepositoryImpl(genreServiceApi);
    }

    @Provides
    @Singleton
    MoviesRepository providesMovieRepository(MoviesServiceApi moviesServiceApi) {
        return new MoviesRepositoryImpl(moviesServiceApi);
    }

    @Provides
    @Singleton
    VideosRepository providesVideoRepository(VideoServiceApi videoServiceApi) {
        return new VideosRepositoryImpl(videoServiceApi);
    }

}
