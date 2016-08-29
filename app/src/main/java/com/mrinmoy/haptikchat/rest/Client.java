package com.mrinmoy.haptikchat.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    public static final String CHAT_URL = "http://haptik.co/android/test_data/";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(CHAT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static Retrofit getClient() {
        return retrofit;
    }
}
