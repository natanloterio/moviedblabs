package com.natanloterio.moviedb.presentation.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.natanloterio.moviedb.presentation.movie.list.MoviesFragment;

/**
 * Created by natanloterio on 05/06/16.
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MoviesFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }
}