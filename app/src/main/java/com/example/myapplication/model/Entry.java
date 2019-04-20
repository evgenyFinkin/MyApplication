package com.example.myapplication.model;

public class Entry {
    private String title;
    private String descriptions;

    public Entry(String title, String descriptions) {
        this.title = title;
        this.descriptions = descriptions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
