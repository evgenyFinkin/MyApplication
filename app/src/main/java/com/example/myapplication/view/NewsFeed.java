package com.example.myapplication.view;

import com.example.myapplication.model.Entry;
import com.example.myapplication.server.Connection;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsFeed extends ViewModel implements FeedInterface {
    private static final String TAG = "Feed";

    private MutableLiveData<ArrayList<Entry>> feedList;
    private ArrayList<Entry> entries = new ArrayList<>();
    private MutableLiveData<Entry> entry = new MutableLiveData<Entry>();
    public LiveData<ArrayList<Entry>> getFeedList()   {
        return feedList;
    }
    public LiveData<Entry> getEntry()   {
        return entry;
    }

    private Connection connection;

    public void init(String rout){
        if(feedList != null){
            return;
        }
        feedList = new MutableLiveData<>();
        connection = Connection.getInstance();
        connection.openConnection(rout,entries);
        feedList.setValue(entries);
    }
    public void setEntry(int i)  {
        entry.postValue(getFeedList().getValue().get(i));
    }

    public void reLoad(String rout) {
        if(feedList != null){
            connection.openConnection(rout,entries);
            feedList.setValue(entries);
        }
    }
}
