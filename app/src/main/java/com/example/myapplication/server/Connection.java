package com.example.myapplication.server;

import android.util.Log;

import com.example.myapplication.model.Entry;
import com.example.myapplication.model.RSS;
import com.example.myapplication.util.RSSContent;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Connection {
    private static final String TAG = "Connection";

    private RSSContent rssContent = new RSSContent();
    //singleton
    private static final Connection connection = new Connection();
    private Connection() {}
    public static Connection getInstance() {
        return connection;
    }

    public void openConnection(String rout, ArrayList<Entry> entries) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FeedAPI.BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();
        FeedAPI feedAPI = retrofit.create(FeedAPI.class);
        Call<RSS> call = feedAPI.getRSS(rout);
        Log.d(TAG, "openConnection: " + rout);

        call.enqueue(new Callback<RSS>() {
            @Override
            public void onResponse(Call<RSS> rss, Response<RSS> response) {
                Log.d(TAG, "onResponse: " + response.body().getChannel().getTitle());
                assert response.body() != null;
                rssContent.setFeed(response.body(), entries);
            }

            @Override
            public void onFailure(Call<RSS> rss, Throwable t) {

            }});

    }
}
