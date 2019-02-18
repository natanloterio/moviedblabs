package com.natanloterio.moviedb.presentation.movie.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.natanloterio.moviedb.MovieDBLabsApplication;
import com.natanloterio.moviedb.R;
import com.natanloterio.moviedb.data.genre.Genre;
import com.natanloterio.moviedb.data.movie.Movie;
import com.natanloterio.moviedb.data.poster.PosterProvider;
import com.natanloterio.moviedb.presentation.custom.ui.EndlessScrollListener;
import com.natanloterio.moviedb.presentation.movie.detail.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by natanloterio on 05/06/16.
 */
public class MoviesFragment extends Fragment implements MoviesContract.View, EndlessScrollListener.ScrollCallback {


    private MovieAdapter adapter;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    private int maxPage = 0;

    @BindView(R.id.movie_grid)
    GridView gridView;

    @BindView(R.id.movie_load_progress)
    View movieLoadProgress;

    private Snackbar retrySnackbar;

    @Inject
    MoviesContract.UserActionsListener userActionsListener;

    @Inject
    PosterProvider posterProvider;

    public MoviesFragment() {

    }


    private void initAdapter() {
        adapter = new MovieAdapter(new ArrayList<Movie>(), new ArrayList<Genre>(), posterProvider);
    }

    private void initUserActionsListener() {
        userActionsListener.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        startLoad();
    }

    private void startLoad() {

        removeRetry();
        userActionsListener.loadMovies();
        userActionsListener.loadGenres();
    }

    private void removeRetry() {
        if (retrySnackbar != null) {
            retrySnackbar.dismiss();
        }

        retrySnackbar = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, rootView);

        ((MovieDBLabsApplication) getActivity().getApplication()).getAppComponent().inject(this);

        initAdapter();
        initUserActionsListener();


        initGridView();
        initSwipeContainer();

        return rootView;
    }

    private void initSwipeContainer() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {
                startRefresh();
            }

        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,

                android.R.color.holo_green_light,

                android.R.color.holo_orange_light,

                android.R.color.holo_red_light);
    }

    @OnItemClick(R.id.movie_grid)
    public void onMovieClick(AdapterView<?> parent, int position) {
        showMovieDetail(position);
    }

    private void initGridView() {
        gridView.setAdapter(adapter);
        gridView.setOnScrollListener(new EndlessScrollListener(this));
    }

    private void showMovieDetail(int position) {
        Intent intent = MovieDetailActivity.getIntent(getActivity(), ((Movie) adapter.getItem
                (position)).getId());
        startActivity(intent);
    }

    private void startRefresh() {
        adapter.clear();
        adapter.notifyDataSetChanged();

        startLoad();
    }

    @Override
    public void showActivityIndicator() {
        swipeContainer.setRefreshing(true);
    }

    @Override
    public void hideActivityIndicator() {
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void showMovies(List<Movie> movies, int currentPage, int maxPage) {
        this.maxPage = maxPage;

        adapter.clear();
        adapter.addAll(movies);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable e) {
        Log.d("error", Log.getStackTraceString(e));

        retrySnackbar = Snackbar
                .make(gridView, e.getMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startLoad();
                    }
                });

        retrySnackbar.show();
    }

    @Override
    public void appendPage(List<Movie> movies, int currentPage) {
        adapter.addAll(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showGenres(List<Genre> genres) {
        this.adapter.setGenres(genres);
        this.gridView.invalidateViews();
    }

    @Override
    public void showNoMoviesFoundView() {
        // TODO: 26/11/16  
    }

    public void showPageLoad() {
        this.movieLoadProgress.setVisibility(View.VISIBLE);
    }

    public void hidePageLoad() {
        movieLoadProgress.setVisibility(View.GONE);
        swipeContainer.setRefreshing(false);
    }

    @Override
    public boolean onLoadMore(int page, int totalItemsCount) {
        if (page <= maxPage) {
            userActionsListener.loadPage(page);
            return true;
        } else {
            return false;
        }
    }
}