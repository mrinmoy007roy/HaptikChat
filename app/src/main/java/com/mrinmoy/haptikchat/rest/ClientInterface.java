package com.mrinmoy.haptikchat.rest;


import com.mrinmoy.haptikchat.model.ChatResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientInterface {
    @GET("./")
    Call<ChatResponse> getChatDetails();
}