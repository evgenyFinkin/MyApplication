package com.example.myapplication.pojo;

import com.example.myapplication.model.Item;
import com.example.myapplication.model.RSS;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Response;

public class RSSContent{
    private static final String TAG = "RSSContent";

    private RSSContent(){}
    private static final RSSContent instance = new RSSContent();
    public static RSSContent getInstance() {
        return instance;
    }

    private ParseFeed parseFeed = ParseFeed.getInstance();

    public Set<String> getDescription(RSS feed) {
        Set<String> description = new HashSet<String>();
            for(Item item: feed.getChannel().getItem()) {
                if(!item.getDescription().isEmpty())    {
                    description.add(parseFeed.parseContent(item.getDescription()));
                }else {
                    description.add("No description");
                }
            }
        return description;
    }

    public Set<String> getTitles(RSS feed) {
        Set<String> titles = new HashSet<String>();
        for(Item item: feed.getChannel().getItem()) {
            if(!item.getDescription().isEmpty())    {
                titles.add(item.getTitle());
            }else{
                titles.add("No title");
            }
        }
        return titles;
    }


}
