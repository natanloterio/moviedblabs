package com.natanloterio.moviedb.injection;

import com.natanloterio.moviedb.data.backdrop.AssetBackdropImageProvider;
import com.natanloterio.moviedb.data.backdrop.BackdropImageProvider;
import com.natanloterio.moviedb.data.backdrop.BackdropServiceApi;
import com.natanloterio.moviedb.data.backdrop.FakeBackdropServiceApi;
import com.natanloterio.moviedb.data.genre.GenresServiceApi;
import com.natanloterio.moviedb.data.genres.FakeGenresServiceApiImpl;
import com.natanloterio.moviedb.data.movie.MoviesServiceApi;
import com.natanloterio.moviedb.data.movies.FakeMoviesServiceApiImpl;
import com.natanloterio.moviedb.data.poster.AssetPosterProvider;
import com.natanloterio.moviedb.data.poster.PosterProvider;
import com.natanloterio.moviedb.data.thumbnail.AssetThumbnailProvider;
import com.natanloterio.moviedb.data.thumbnail.ThumbnailProvider;
import com.natanloterio.moviedb.data.video.FakeVideosServiceApiImpl;
import com.natanloterio.moviedb.data.video.VideoServiceApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by natanloterio on 05/12/16.
 */

@Module
public class DataModule {


    @Provides
    @Singleton
    BackdropServiceApi providesBackdropServiceApi() {
        return new FakeBackdropServiceApi();
    }


    @Provides
    @Singleton
    GenresServiceApi providesGenreServiceApi() {
        return new FakeGenresServiceApiImpl();
    }

    @Provides
    @Singleton
    VideoServiceApi providesVideoServiceApi() {
        return new FakeVideosServiceApiImpl();
    }

    @Provides
    @Singleton
    MoviesServiceApi providesMovieServiceApi() {
        return new FakeMoviesServiceApiImpl();
    }

    @Provides
    @Singleton
    ThumbnailProvider providesThumbnailprovider() {
        return new AssetThumbnailProvider();
    }

    @Provides
    @Singleton
    PosterProvider providesPosterProvider() {
        return new AssetPosterProvider();
    }

    @Provides
    @Singleton
    BackdropImageProvider providesBackdropImageProvider() {
        return new AssetBackdropImageProvider();
    }

}