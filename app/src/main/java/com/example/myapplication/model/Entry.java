package com.example.myapplication.model;

import androidx.lifecycle.LiveData;

public class Entry {
    private String title = "No title";
    private String description = "No description";

    public Entry(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
