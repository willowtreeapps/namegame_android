package com.willowtreeapps.namegame;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class SynchronousCallAdapter<T> extends CallAdapter<T> {

    protected abstract Response<T> response();

    @Override
    public Response<T> execute() throws IOException {
        return response();
    }

    @Override
    public void enqueue(Callback<T> callback) {
        try {
            Response<T> response = execute();
            if (response.isSuccess()) {
                callback.onResponse(this, response);
            } else {
                throw new IOException(response.errorBody().string());
            }
        } catch (IOException e) {
            callback.onFailure(this, e);
        }
    }

    public static <T> SynchronousCallAdapter<T> forSuccess(@NonNull final T body) {
        return new SynchronousCallAdapter<T>() {
            @Override
            protected Response<T> response() {
                return Response.success(body);
            }
        };
    }

    public static <T> SynchronousCallAdapter<T> forError() {
        return new SynchronousCallAdapter<T>() {
            @Override
            protected Response<T> response() {
                return Response.error(404, ResponseBody.create(MediaType.parse("text/plain"), "Try again next time!"));
            }
        };
    }

    public static <T> SynchronousCallAdapter<T> forError(final int code) {
        return new SynchronousCallAdapter<T>() {
            @Override
            protected Response<T> response() {
                return Response.error(code, ResponseBody.create(MediaType.parse("text/plain"), "Try again next time!"));
            }
        };
    }

    public static <T> SynchronousCallAdapter<T> forError(final String body) {
        return new SynchronousCallAdapter<T>() {
            @Override
            protected Response<T> response() {
                return Response.error(404, ResponseBody.create(MediaType.parse("text/plain"), body));
            }
        };
    }

    public static <T> SynchronousCallAdapter<T> forError(final int code, final String body) {
        return new SynchronousCallAdapter<T>() {
            @Override
            protected Response<T> response() {
                return Response.error(code, ResponseBody.create(MediaType.parse("text/plain"), body));
            }
        };
    }
}
