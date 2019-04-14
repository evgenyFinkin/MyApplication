package com.example.myapplication.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;

@Root(name = "channel", strict = false)
public class Channel implements Serializable {

    @Element(name = "title")
    private String title;

    @ElementList(name = "item", inline = true)
    private List<Item> item;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public String toString() {
        return "\n" +
               "Item: \n [Item: +" +  item + " ]";
    }
}
