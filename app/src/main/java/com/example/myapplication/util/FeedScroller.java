package com.example.myapplication.util;

import com.example.myapplication.model.Entry;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;

public class FeedScroller extends Thread {
    private int i = 0;
    private boolean flag = false;
    private int size;

    FeedScroller(int size){
        this.size = size;
    }

    @Override
    public void run() {
        while (size>i)  {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
