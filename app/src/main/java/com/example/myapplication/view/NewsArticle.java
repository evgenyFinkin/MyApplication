package com.example.myapplication.view;

import android.util.Log;

import com.example.myapplication.model.Entry;
import com.example.myapplication.server.Connection;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsArticle extends ViewModel{
    private static final String TAG = "NewsArticle";
    private MutableLiveData<ArrayList<Entry>> newsArticle;
    public LiveData<ArrayList<Entry>> getNewsArticle()   {
        return newsArticle;
    }
    private Connection connection;

    public void setNewsArticle(ArrayList<Entry> entry)  {
        newsArticle.setValue(entry);
    }

    public void init(){
        if(newsArticle != null){
            return;
        }
        connection = Connection.getInstance();
        connection.openConnection();
        newsArticle = connection.getNewsFeed();
    }


}
