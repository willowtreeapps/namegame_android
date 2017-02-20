package com.willowtreeapps.namegame.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.willowtreeapps.namegame.BuildConfig;
import com.willowtreeapps.namegame.network.api.NameGameApi;
import com.willowtreeapps.namegame.network.api.ProfilesRepository;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public static final String NETWORK_CACHE = "network_cache";

    private static final int GLOBAL_TIMEOUT = 30; // seconds

    @NonNull
    private final HttpUrl endpoint;

    public NetworkModule(@NonNull String endpoint) {
        this(HttpUrl.parse(endpoint));
    }

    public NetworkModule(@NonNull HttpUrl endpoint) {
        this.endpoint = endpoint;
    }

    @Provides @NonNull @Singleton
    public HttpUrl provideEndpoint() {
        return this.endpoint;
    }

    @Provides @NonNull @Singleton @Named(NETWORK_CACHE)
    public File provideNetworkCacheDirectory(@NonNull Context context) {
        return context.getDir(NETWORK_CACHE, Context.MODE_PRIVATE);
    }

    @Provides @NonNull @Singleton
    public Cache provideNetworkCache(@NonNull @Named(NETWORK_CACHE) File cacheDir) {
        int cacheSize = 20 * 1024 * 1024; // 20 MiB
        return new Cache(cacheDir, cacheSize);
    }

    @Provides @NonNull @Singleton
    public OkHttpClient provideHttpClient(@NonNull Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(GLOBAL_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(GLOBAL_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(GLOBAL_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides @NonNull @Singleton
    public NameGameApi provideApi(@NonNull Gson gson, @NonNull OkHttpClient client, @NonNull HttpUrl url) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .validateEagerly(BuildConfig.DEBUG)
                .build()
                .create(NameGameApi.class);
    }

    @Provides @NonNull @Singleton
    public ProfilesRepository providePersonRepository(@NonNull NameGameApi api) {
        return new ProfilesRepository(api);
    }

    @Provides @NonNull @Singleton
    public Picasso providePicasso(@NonNull Context context, @NonNull OkHttpClient client) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .defaultBitmapConfig(Bitmap.Config.ARGB_8888)
                .build();
    }

}
