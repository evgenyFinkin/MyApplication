package com.example.myapplication.pojo;

public class ParseFeed {
    private ParseFeed() {}
    private static final ParseFeed instance = new ParseFeed();
    private static final String TAG = "ParseFeed";

    public static ParseFeed getInstance() {
        return instance;
    }

    public String parseContent (String description) {
        return description.substring(0,description.indexOf("<div"));
    }

}
