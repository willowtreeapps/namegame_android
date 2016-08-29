package com.willowtreeapps.namegame.network.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NameGameApi {

    @GET("http://api.namegame.willowtreemobile.com/")
    Call<List<Person>> getPeople();

}
