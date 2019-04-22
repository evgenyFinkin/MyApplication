package com.example.myapplication.server;

import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.model.Entry;
import com.example.myapplication.model.Item;
import com.example.myapplication.model.RSS;
import com.example.myapplication.ui.MainActivity;
import com.example.myapplication.util.RSSContent;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Connection {
    private static final String TAG = "Connection";

    private RSSContent rssContent = RSSContent.getInstance();
    private ArrayList<Entry> entry = new ArrayList<Entry>();
    private MutableLiveData<ArrayList<Entry>> newsFeed= new MutableLiveData<>();

    public MutableLiveData<ArrayList<Entry>> getNewsFeed()  {
        return newsFeed;
    }

    //singleton
    private static final Connection connection = new Connection();
    private Connection() {}
    public static Connection getInstance() {
        return connection;
    }


    Retrofit retrofit = new Retrofit.Builder().baseUrl(FeedAPI.BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create()).build();
    FeedAPI feedAPI = retrofit.create(FeedAPI.class);
    Call<RSS> call = feedAPI.getRSS();

    public void openConnection() {
        call.enqueue(new Callback<RSS>() {
            @Override
            public void onResponse(Call<RSS> rss, Response<RSS> response) {
                assert response.body() != null;

            /*Log.d(TAG,"onResponse, RSS feed: " + response.body().getChannel().getItem().toString());
            Log.d(TAG,"onResponse, Server response:" + response.toString());*/
            rssContent.setFeed(response.body(),entry);
            newsFeed.setValue(entry);


            }

            @Override
            public void onFailure(Call<RSS> rss, Throwable t) {
            /*Log.e(TAG,"onFailure: Was unable to retrieve RSS"
                    + t.getMessage());*/
            }
        });
    }
}
