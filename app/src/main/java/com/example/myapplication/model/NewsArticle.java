package com.example.myapplication.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class NewsArticle{
    private static final String TAG = "NewsArticle";
    private Entry entry;

    public Entry getLiveEntry() {
        return entry;
    }

    public void setLiveEntry(Entry liveEntry) {
        this.entry = liveEntry;
    }

}
