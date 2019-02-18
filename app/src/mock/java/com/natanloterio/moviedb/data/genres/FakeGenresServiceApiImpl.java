package com.natanloterio.moviedb.data.genres;

import android.support.v4.util.ArrayMap;

import com.natanloterio.moviedb.data.genre.Genre;
import com.natanloterio.moviedb.data.genre.GenresServiceApi;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by natanloterio on 03/08/2016.
 */
public class FakeGenresServiceApiImpl implements GenresServiceApi {


    private static final ArrayMap<Integer, Genre> GENRES_SERVICE_DATA = new ArrayMap<>();

    @Override
    public void getGenres(GenresServiceCallback<List<Genre>> callback) {

        if (GENRES_SERVICE_DATA.isEmpty()) {
            for (int i = 0; i < 100; i++) {
                Genre genre = new Genre();
                genre.setId(i);
                genre.setName("Genre " + i);

                GENRES_SERVICE_DATA.put(genre.getId(), genre);
            }

        }
        callback.onLoaded(Lists.newArrayList(GENRES_SERVICE_DATA.values()));
    }
}
