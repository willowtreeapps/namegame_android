package com.willowtreeapps.namegame.network.api;

import com.willowtreeapps.namegame.network.api.model.Profiles;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NameGameApi {
    @GET("/api/v1.0/profiles")
    Call<Profiles> getProfiles();
}
