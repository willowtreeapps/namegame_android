package com.willowtreeapps.namegame;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class CallAdapter<T> implements Call<T> {

    @Override
    public Response<T> execute() throws IOException {
        return null;
    }

    @Override
    public void enqueue(Callback<T> callback) {
        //NOP
    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {
        //NOP
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public Call<T> clone() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Request request() {
        return null;
    }
}
