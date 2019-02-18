package com.natanloterio.moviedb.data.movies;

import android.content.Context;
import android.support.annotation.NonNull;

import com.natanloterio.moviedb.data.cloud.MovieDBRESTApi;
import com.natanloterio.moviedb.data.movie.Movie;
import com.natanloterio.moviedb.data.movie.MovieTransport;
import com.natanloterio.moviedb.data.movie.MoviesServiceApi;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by natanloterio on 08/08/2016.
 */
public class MoviesServiceApiEndpoint implements MoviesServiceApi {

    private final Context context;
    private final MovieDBRESTApi service;

    @Inject
    public MoviesServiceApiEndpoint(@NonNull @Named("applicationContext") Context context,
                                    @NonNull MovieDBRESTApi service) {
        this.context = checkNotNull(context, "context can not be null");
        this.service = checkNotNull(service, "service can not be null");
    }

    @Override
    public void getMovies(final int page, final MoviesServiceCallback<List<Movie>> callback) {

        Call<MovieTransport> response = service.orderByPopularity(page);
        response.enqueue(new Callback<MovieTransport>() {
            @Override
            public void onResponse(Call<MovieTransport> call, Response<MovieTransport> response) {

                if (response.isSuccessful()) {
                    callback.onLoaded(response.body().getResults(), page, response.body()
                            .getTotalPages());
                } else {
                    try {
                        String error = response.errorBody().string();
                        callback.onError(new Exception(error));
                    } catch (Exception e) {
                        callback.onError(new Exception());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieTransport> call, Throwable t) {
                callback.onError(t);
            }
        });

    }

    @Override
    public void getMovie(int movieId, final MovieServiceCallback callback) {
        Call<Movie> call = service.getMovie(movieId);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    callback.onLoadMovie(response.body());
                } else {
                    try {
                        String error = response.errorBody().string();
                        callback.errorLoadingMovie(new Exception(error));
                    } catch (Exception e) {
                        callback.errorLoadingMovie(new Exception());
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.errorLoadingMovie(t);
            }
        });
    }
}
