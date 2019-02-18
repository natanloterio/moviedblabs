package com.natanloterio.moviedb.presentation.movie.list;

import com.natanloterio.moviedb.data.genre.Genre;
import com.natanloterio.moviedb.data.genre.GenresRepository;
import com.natanloterio.moviedb.data.movie.Movie;
import com.natanloterio.moviedb.data.movie.MoviesRepository;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by natanloterio on 26/11/16.
 */
public class MoviesPresenterTest {


    private static final List<Genre> GENRES = Lists.newArrayList(new Genre(), new Genre());

    private static final List<Genre> EMPTY_GENRES = Lists.newArrayList();

    private static final List<Movie> MOVIES = Lists.newArrayList(new Movie(), new Movie());

    private static final List<Movie> EMPTY_MOVIES = Lists.newArrayList();

    @Mock
    private MoviesRepository moviesRepository;

    @Mock
    private MoviesContract.View moviesView;

    @Mock
    private GenresRepository genresRepository;

    private MoviesPresenter presenter;

    @Captor
    private ArgumentCaptor<Integer> pageCaptor;

    @Captor
    private ArgumentCaptor<MoviesRepository.LoadMoviesCallback> loadMoviesCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<GenresRepository.LoadGenresCallback> loadGenresArgumentCaptor;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        presenter = new MoviesPresenter(moviesRepository, genresRepository);
        presenter.setView(moviesView);
    }


    @Test
    public void loadMovies() throws Exception {

        int maxPage = 10;
        presenter.loadMovies();

        verify(moviesRepository).getMovies(pageCaptor.capture(), loadMoviesCallbackArgumentCaptor.capture());

        loadMoviesCallbackArgumentCaptor.getValue().onLoadMovies(MOVIES, pageCaptor.getValue(), maxPage);

        InOrder inOrder = Mockito.inOrder(moviesView);

        inOrder.verify(moviesView).hideActivityIndicator();
        inOrder.verify(moviesView).showMovies(MOVIES, pageCaptor.getValue(), maxPage);
    }

    @Test
    public void loadMovies_error() throws Exception {

        presenter.loadMovies();

        verify(moviesRepository).getMovies(pageCaptor.capture(), loadMoviesCallbackArgumentCaptor.capture());

        Exception exception = Mockito.mock(Exception.class);
        loadMoviesCallbackArgumentCaptor.getValue().onErrorLoadingMovies(exception);

        InOrder inOrder = Mockito.inOrder(moviesView);

        inOrder.verify(moviesView).hideActivityIndicator();
        inOrder.verify(moviesView).showError(exception);
    }

    @Test
    public void loadMovies_empty() throws Exception {

        int maxPage = 10;
        presenter.loadMovies();

        verify(moviesRepository)
                .getMovies(pageCaptor.capture(), loadMoviesCallbackArgumentCaptor.capture());

        loadMoviesCallbackArgumentCaptor
                .getValue()
                .onLoadMovies(EMPTY_MOVIES, pageCaptor.getValue(), maxPage);

        InOrder inOrder = Mockito.inOrder(moviesView);

        inOrder.verify(moviesView).hideActivityIndicator();
        inOrder.verify(moviesView).showNoMoviesFoundView();
    }

    @Test
    public void loadPage() throws Exception {

        int page = 2;
        int maxPage = 10;
        presenter.loadPage(page);

        verify(moviesRepository).getMovies(pageCaptor.capture(), loadMoviesCallbackArgumentCaptor.capture());

        loadMoviesCallbackArgumentCaptor.getValue().onLoadMovies(MOVIES, page, maxPage);

        InOrder inOrder = Mockito.inOrder(moviesView);

        inOrder.verify(moviesView).hidePageLoad();
        inOrder.verify(moviesView).appendPage(MOVIES, pageCaptor.getValue());
    }

    @Test
    public void loadPage_error() throws Exception {

        int page = 2;
        presenter.loadPage(page);

        verify(moviesRepository).getMovies(pageCaptor.capture(), loadMoviesCallbackArgumentCaptor.capture());

        Exception exception = Mockito.mock(Exception.class);

        loadMoviesCallbackArgumentCaptor.getValue().onErrorLoadingMovies(exception);

        InOrder inOrder = Mockito.inOrder(moviesView);

        inOrder.verify(moviesView).hidePageLoad();
        inOrder.verify(moviesView).showError(exception);
    }

    @Test
    public void loadPage_empty() throws Exception {

        int page = 2;
        int maxPage = 10;
        presenter.loadPage(page);

        verify(moviesRepository).getMovies(pageCaptor.capture(), loadMoviesCallbackArgumentCaptor.capture());

        loadMoviesCallbackArgumentCaptor.getValue().onLoadMovies(EMPTY_MOVIES, page, maxPage);

        InOrder inOrder = Mockito.inOrder(moviesView);

        inOrder.verify(moviesView).hidePageLoad();
        inOrder.verify(moviesView).appendPage(EMPTY_MOVIES, pageCaptor.getValue());
    }

    @Test
    public void loadGenres() throws Exception {
        presenter.loadGenres();

        verify(genresRepository).getGenres(loadGenresArgumentCaptor.capture());

        loadGenresArgumentCaptor.getValue().onLoadGenres(GENRES);

        InOrder inOrder = Mockito.inOrder(moviesView);

        inOrder.verify(moviesView).showGenres(GENRES);
    }

    @Test
    public void loadGenres_error() throws Exception {
        presenter.loadGenres();

        verify(genresRepository).getGenres(loadGenresArgumentCaptor.capture());

        Exception exception = Mockito.mock(Exception.class);

        loadGenresArgumentCaptor.getValue().onError(exception);

        InOrder inOrder = Mockito.inOrder(moviesView);

        inOrder.verify(moviesView).showError(exception);
    }

    @Test
    public void loadGenres_empty() throws Exception {
        presenter.loadGenres();

        verify(genresRepository).getGenres(loadGenresArgumentCaptor.capture());

        loadGenresArgumentCaptor.getValue().onLoadGenres(EMPTY_GENRES);

        InOrder inOrder = Mockito.inOrder(moviesView);

        inOrder.verify(moviesView).showGenres(EMPTY_GENRES);
    }

}