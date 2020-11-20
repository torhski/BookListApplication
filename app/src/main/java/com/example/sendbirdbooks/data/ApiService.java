package com.example.sendbirdbooks.data;

import com.example.sendbirdbooks.data.Response.NewResponse;
import com.example.sendbirdbooks.data.model.BookDataEntity;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/1.0/new")
    Single<NewResponse> fetchNewData();

    @GET("/1.0/books/{id}")
    Single<BookDataEntity> fetchDetailBook(@Path("id") String id);
}
