package com.example.myapplication.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

import androidx.annotation.NonNull;

@Root(name = "item", strict = false)
public class Item implements Serializable {

    @Element(required = false ,name = "title")
    private String title;

    @Element(required = false ,name = "description")
    private String description;

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

    public Item(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Item() {

    }
/*
    @NonNull
    @Override
    public String toString() {
        return  "\n" +
                "title: " + title +"\n"
                + "description: " + description + "\n"
                + "---------------------------------------------------------------------------------";
    }

*/
}
