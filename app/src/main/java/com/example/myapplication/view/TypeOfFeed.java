package com.example.myapplication.view;

import android.util.Log;

import com.example.myapplication.model.Entry;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TypeOfFeed extends ViewModel {
    private static final String TAG = "NewsFeed";

    private MutableLiveData<Boolean> feedType = new MutableLiveData<>();
    public LiveData<Boolean> getFeedType()   {
        return feedType;
    }

    public void setFeedType(Boolean type){feedType.setValue(type);}
    public void init()  {
        feedType.setValue(true);
    }

}
