package com.example.myapplication.view;

import android.util.Log;

import com.example.myapplication.model.Entry;
import com.example.myapplication.server.Connection;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntertainmentArticle extends ViewModel{
    private static final String TAG = "EntertainmentArticle";
    private MutableLiveData<ArrayList<Entry>> entertainmentArticle;
    private ArrayList<Entry> entries = new ArrayList<>();
    private MutableLiveData<Entry> entry = new MutableLiveData<Entry>();

    public LiveData<ArrayList<Entry>> getNewsArticles()   {
        return entertainmentArticle;
    }
    public LiveData<Entry> getEntry()   {
        return entry;
    }

    private Connection connection;

    public void init(){
        if(entertainmentArticle != null){
            return;
        }
        entertainmentArticle = new MutableLiveData<>();
        connection = Connection.getInstance();
        connection.openConnection("entertainment",entries);
        entertainmentArticle.setValue(entries);
    }
    public void setEntry(int i)  {
        entry.postValue(getNewsArticles().getValue().get(i));
        Log.d(TAG, "setEntry: " + getNewsArticles().getValue().get(i).getTitle());
    }
}
