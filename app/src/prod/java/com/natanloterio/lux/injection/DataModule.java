package com.natanloterio.moviedb.injection;

import android.content.Context;

import com.natanloterio.moviedb.data.backdrop.BackdropImageProvider;
import com.natanloterio.moviedb.data.backdrop.BackdropImageProviderImpl;
import com.natanloterio.moviedb.data.backdrop.BackdropServiceApi;
import com.natanloterio.moviedb.data.backdrop.BackdropServiceApiEndpoint;
import com.natanloterio.moviedb.data.cloud.MovieDBRESTApi;
import com.natanloterio.moviedb.data.genre.GenresServiceApi;
import com.natanloterio.moviedb.data.genre.GenresServiceApiEndpoint;
import com.natanloterio.moviedb.data.movie.MoviesServiceApi;
import com.natanloterio.moviedb.data.movies.MoviesServiceApiEndpoint;
import com.natanloterio.moviedb.data.poster.PosterProvider;
import com.natanloterio.moviedb.data.poster.PosterProviderImpl;
import com.natanloterio.moviedb.data.thumbnail.ThumbnailProvider;
import com.natanloterio.moviedb.data.thumbnail.YoutubeThumbnailProvider;
import com.natanloterio.moviedb.data.video.VideoServiceApi;
import com.natanloterio.moviedb.data.videos.VideoServiceApiEndpoint;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by natanloterio on 06/12/16.
 */


@Module
public class DataModule {

    @Provides
    @Singleton
    BackdropServiceApi providesBackdropServiceApi(@Named("applicationContext") Context context, MovieDBRESTApi service) {
        return new BackdropServiceApiEndpoint(context, service);
    }


    @Provides
    @Singleton
    GenresServiceApi providesGenreServiceApi(@Named("applicationContext") Context context, MovieDBRESTApi service) {
        return new GenresServiceApiEndpoint(context, service);
    }

    @Provides
    @Singleton
    VideoServiceApi providesVideoServiceApi(@Named("applicationContext") Context context, MovieDBRESTApi service) {
        return new VideoServiceApiEndpoint(context, service);
    }

    @Provides
    @Singleton
    MoviesServiceApi providesMovieServiceApi(@Named("applicationContext") Context context,
                                             MovieDBRESTApi service) {
        return new MoviesServiceApiEndpoint(context,service);
    }

    @Provides
    @Singleton
    ThumbnailProvider providesThumbnailprovider() {
        return new YoutubeThumbnailProvider();
    }

    @Provides
    @Singleton
    PosterProvider providesPosterProvider() {
        return new PosterProviderImpl();
    }

    @Provides
    @Singleton
    BackdropImageProvider providesBackdropImageProvider() {
        return new BackdropImageProviderImpl();
    }
}
