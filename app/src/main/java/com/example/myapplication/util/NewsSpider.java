package com.example.myapplication.util;


import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.view.NewsArticle;

import androidx.lifecycle.ViewModelProviders;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NewsSpider extends Thread {
    private static final String TAG = "NewsSpider";

    private int indx = 0;
    private NewsArticle newsArticle;
    private Boolean state = false;

    public void setState(Boolean state) {
        this.state = state;
    }

    public NewsSpider(NewsArticle newsArticle) {
        this.newsArticle = newsArticle;
    }

    @Override
    public void run() {
        Looper.prepare();
        if(newsArticle != null)    {
            while (state){
                indx = (indx < newsArticle
                        .getNewsArticles()
                        .getValue().size() - 1) ? ++indx : 0;
                newsArticle.setEntry(indx);
                if( newsArticle.getEntry().getValue() != null)
                Log.d(TAG, "run: " + newsArticle.getEntry().getValue().getTitle());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        Looper.loop();
    }
}
