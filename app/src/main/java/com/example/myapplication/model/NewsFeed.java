package com.example.myapplication.model;

import android.util.Log;
import com.example.myapplication.util.RSSContent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class NewsFeed {
    private static final String TAG = "NewsFeed";
    private RSSContent rssContent = RSSContent.getInstance();
    private ArrayList<Entry> entry = new ArrayList<Entry>();
    private NewsArticle newsArticle = new NewsArticle();

    public void setFeed(RSS body) {
        rssContent.setFeed(body, entry);
        passArticle(entry);
    }

    private void passArticle(ArrayList<Entry> entry) {
        int i = 0;
        while (entry.size()>i)  {
            newsArticle.setLiveEntry(entry.get(i));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

}
