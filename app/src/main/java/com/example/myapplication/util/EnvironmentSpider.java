package com.example.myapplication.util;


import android.os.Looper;
import android.util.Log;

import com.example.myapplication.view.EnvironmentArticle;

public class EnvironmentSpider extends Thread {
    private static final String TAG = "EnvironmentSpider";
    private int indx = 0;
    private EnvironmentArticle entertainmentArticle;
    private Boolean state = false;

    public void setState(Boolean state) {
        this.state = state;
    }

    public EnvironmentSpider(EnvironmentArticle entertainmentArticle) {
        this.entertainmentArticle = entertainmentArticle;
    }

    @Override
    public void run() {
        Looper.prepare();
        if(entertainmentArticle != null)    {
            while (state){
                indx = (indx < entertainmentArticle
                        .getNewsArticles()
                        .getValue().size() - 1) ? ++indx : 0;
                entertainmentArticle.setEntry(indx);
                if( entertainmentArticle.getEntry().getValue() != null)
                    Log.d(TAG, "run: " + entertainmentArticle.getEntry().getValue().getTitle());
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
