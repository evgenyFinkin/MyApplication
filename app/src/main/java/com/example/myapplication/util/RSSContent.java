package com.example.myapplication.util;

import com.example.myapplication.model.Entry;
import com.example.myapplication.model.Item;
import com.example.myapplication.model.RSS;

import java.util.ArrayList;

public class RSSContent{
    private static final String TAG = "RSSContent";
    private Item item;

    public void setFeed (RSS rss, ArrayList<Entry> feed) {
        int i = 0;
        while (rss.getChannel().getItem().size()>i)    {
            item = rss.getChannel().getItem().get(i);
            feed.add(new Entry(item.getTitle(), parseContent(item.getDescription())));
            i++;
        }
    }

    private String parseContent (String description) {
        return description.substring(0,description.indexOf("<div"));
    }
}
