package com.example.sendbirdbooks.data;

import com.example.sendbirdbooks.data.Response.NewResponse;
import com.example.sendbirdbooks.data.model.BookDataEntity;
import com.google.gson.GsonBuilder;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static ApiService mService = makeService();

    private static ApiService makeService() {
        return new Retrofit.Builder()
                .baseUrl("https://api.itbook.store")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build()
                .create(ApiService.class);
    }

    public static Single<NewResponse> fetchNewData() {
        return mService.fetchNewData();
    }
    public static Single<BookDataEntity> fetchDetailData(String id) { return mService.fetchDetailBook(id);}
}
