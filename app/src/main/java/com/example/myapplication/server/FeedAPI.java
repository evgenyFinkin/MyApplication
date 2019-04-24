package com.example.myapplication.server;

import com.example.myapplication.model.RSS;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FeedAPI {
    String BASE_URL = "http://feeds.reuters.com/reuters/";

    //Non-static feed name
    /*@GET("{feed}")
    Call<RSS> getRSS(@Path("feed") String feedName );*/


    @GET("businessNews")
    Call<RSS> getRSS();
}
