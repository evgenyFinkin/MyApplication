package com.example.myapplication.model;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.pojo.RSSContent;
import com.example.myapplication.ui.SecondTabFragment;

import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NewsFeed {
    private static final String TAG = "RSSContent";
    private RSSContent rssContent = RSSContent.getInstance();

    private ArrayList<String> descriptions = new ArrayList<String>();
    private ArrayList<String> titles = new ArrayList<String>();

    public void setNewsFeed (RSS feed) {
        descriptions.addAll(rssContent.getDescription(feed));
        titles.addAll(rssContent.getTitles(feed));

        if(descriptions != null){
            Log.d(TAG,"RSSContent, descriptions:" + descriptions);
            Bundle descriptionBundle = new Bundle();
            descriptionBundle.putStringArrayList("NewsDescription", descriptions);

            SecondTabFragment secondTabFragment = new SecondTabFragment();
            secondTabFragment.setArguments(descriptionBundle);
        }
    }

}
