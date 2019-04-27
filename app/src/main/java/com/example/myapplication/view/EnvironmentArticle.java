package com.example.myapplication.view;

import android.util.Log;

import com.example.myapplication.model.Entry;
import com.example.myapplication.server.Connection;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EnvironmentArticle extends ViewModel{
    private static final String TAG = "EntertainmentArticle";
    private MutableLiveData<ArrayList<Entry>> environmentArticle;
    private MutableLiveData<Entry> entry = new MutableLiveData<Entry>();

    public LiveData<ArrayList<Entry>> getNewsArticles()   {
        return environmentArticle;
    }
    public LiveData<Entry> getEntry()   {
        return entry;
    }

    private Connection connection;

    public void init(){
        if(environmentArticle != null){
            return;
        }
        connection = Connection.getInstance();
        connection.openConnection("environment");
        environmentArticle = connection.getNewsFeed();
    }
    public void setEntry(int i)  {
        entry.postValue(getNewsArticles().getValue().get(i));
        Log.d(TAG, "setEntry: " + getNewsArticles().getValue().get(i).getTitle());
    }
}
