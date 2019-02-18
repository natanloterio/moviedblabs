package com.natanloterio.moviedb.data.cloud;


import com.natanloterio.moviedb.data.backdrop.BackdropTransport;
import com.natanloterio.moviedb.data.genre.GenreTransport;
import com.natanloterio.moviedb.data.movie.Movie;
import com.natanloterio.moviedb.data.movie.MovieTransport;
import com.natanloterio.moviedb.data.video.VideosTransport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDBRESTApi {
    @GET("discover/movie?sort_by=popularity.desc")
    Call<MovieTransport> orderByPopularity(@Query("page") int page);

    @GET("genre/movie/list")
    Call<GenreTransport> getGenres();

    @GET("movie/{movie_id}/videos")
    Call<VideosTransport> getVideos(@Path("movie_id") Integer movieId);

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(@Path("movie_id") Integer movieId);

    @GET("movie/{movie_id}/images")
    Call<BackdropTransport> getMovieImages(@Path("movie_id") Integer movieId);
}