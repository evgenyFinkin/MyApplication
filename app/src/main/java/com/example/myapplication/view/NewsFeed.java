package com.example.myapplication.view;

import android.os.AsyncTask;

import com.example.myapplication.model.Entry;
import com.example.myapplication.model.RSS;
import com.example.myapplication.util.RSSContent;
import com.example.myapplication.view.NewsArticle;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class NewsFeed extends ViewModel {
    private static final String TAG = "NewsFeed";
    private RSSContent rssContent = RSSContent.getInstance();
    private ArrayList<Entry> entry = new ArrayList<Entry>();
    private MutableLiveData<Entry> newsArticle;

    private void setNewsArticle(Entry entry)  {
        if(newsArticle == null)
            newsArticle = new MutableLiveData<>();
        newsArticle.setValue(entry);
    }

    public LiveData<Entry> getNewsArticle()   {
        return newsArticle;
    }

    public void setFeed(RSS body) {
        rssContent.setFeed(body, entry);
        passArticle(entry);
    }

    private void passArticle(@NonNull ArrayList<Entry> entry) {
        int i = 0;
        while (entry.size()>i)  {
            setNewsArticle(entry.get(i));
            i++;
        }

    }


}
