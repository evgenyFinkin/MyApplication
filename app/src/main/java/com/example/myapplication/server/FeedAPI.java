package com.example.myapplication.server;

import com.example.myapplication.model.RSS;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*public interface FeedAPI {
    String BASE_URL = "http://feeds.reuters.com/reuters/";
    @GET("businessNews")
    Call<RSS> getRSS();

}*/
public interface FeedAPI {
    String BASE_URL = "http://feeds.reuters.com/reuters/";
    @GET("{feedName}")
    Call<RSS> getRSS(@Path("feedName") String feedName);
}