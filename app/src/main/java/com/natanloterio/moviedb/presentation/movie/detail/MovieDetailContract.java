package com.natanloterio.moviedb.presentation.movie.detail;

import android.support.annotation.NonNull;

import com.natanloterio.moviedb.data.backdrop.Backdrop;
import com.natanloterio.moviedb.data.movie.Movie;
import com.natanloterio.moviedb.data.video.Video;

import java.util.List;

/**
 * Created by natanloterio on 25/11/16.
 */

public class MovieDetailContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showVideos(List<Video> videos);

        void showError(String message);

        void showLoadingVideos();

        void hideLoadingVideos();

        void showMovie(Movie movie);

        void hideContainerVideos();

        void watchYoutubeVideo(Video video);

        void showLoadingBackdrops();

        void hideLoadingBackdrops();

        void showBackdrops(List<Backdrop> backdrops);

        void showFullScreenBackdrop(Backdrop backdrop);
    }

    public interface UserActionsListener {
        void loadVideos(int movieId);

        void loadMovie(int movieId);

        void loadBackdrops(int movieId);

        void userClickedVideo(@NonNull Video video);

        void userClickedBackdrop(Backdrop backdrop);

        void setView(MovieDetailContract.View view);
    }
}
