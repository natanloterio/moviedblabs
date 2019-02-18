package com.natanloterio.moviedb;

import android.app.Application;

import com.natanloterio.moviedb.injection.AppComponent;
import com.natanloterio.moviedb.injection.AppModule;
import com.natanloterio.moviedb.injection.DaggerAppComponent;

/**
 * Created by natanloterio on 05/12/16.
 */

public class MovieDBLabsApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = createComponent();
    }

    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }
}
