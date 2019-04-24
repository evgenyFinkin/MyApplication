package com.example.myapplication.util;


import android.content.Context;
import android.os.Looper;

import com.example.myapplication.view.NewsArticle;

import androidx.lifecycle.ViewModelProviders;

public class FeedSpider extends Thread {

    private int indx = 0;
    private NewsArticle newsArticle;

    public FeedSpider(NewsArticle newsArticle) {
        this.newsArticle = newsArticle;
    }

    @Override
    public void run() {
        Looper.prepare();
        if(newsArticle != null)    {
            while (true){
                indx = (indx < newsArticle.getNewsArticles().getValue().size() - 1) ? ++indx : 0;
                newsArticle.setEntry(indx);
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
