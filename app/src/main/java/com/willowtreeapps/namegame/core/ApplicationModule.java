package com.willowtreeapps.namegame.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class ApplicationModule {

    @NonNull
    private final Application application;
    
    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides @NonNull @Singleton
    public Application provideApplication() {
        return this.application;
    }

    @Provides @NonNull @Singleton
    public Context provideContext(@NonNull Application application) {
        return application;
    }

    @Provides @NonNull @Singleton
    public Gson provideGson() {
        return new Gson();
    }

    @Provides @NonNull @Singleton
    public Random provideRandom() {
        return new Random(System.currentTimeMillis());
    }

    @Provides @NonNull @Singleton
    public ListRandomizer provideListRandomizer(@NonNull Random random) {
        return new ListRandomizer(random);
    }

}
