package com.example.myapplication.util;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.model.Entry;
import com.example.myapplication.ui.SecondTabFragment;
import com.example.myapplication.view.NewsArticle;

import java.util.ArrayList;

import androidx.lifecycle.ViewModelProviders;


public class FeedScroller extends Thread {
    private static final String TAG = "FeedScroller";

    private SecondTabFragment fragment;
    private int size;
    private int indx;
    private ArrayList<Entry> mListLiveData = null;

    public FeedScroller(SecondTabFragment fragment, int size, int indx, ArrayList mListLiveData) {
        this.fragment = fragment;
        this.size = size;
        this.indx = indx;
        this.mListLiveData = mListLiveData;
    }


    public void setFragment(SecondTabFragment fragment) {
        this.fragment = fragment;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setIndx(int indx) {
        this.indx = indx;
    }

    public SecondTabFragment getFragment() {
        return fragment;
    }

    public int getIndx() {
        return indx;
    }

    @Override
    public void run() {
        while (true){
            indx = (indx < size - 1) ? ++indx : 0;
            fragment.setmIndx(indx);
            assert fragment.getFragmentManager() != null;
            fragment.getFragmentManager()
                    .beginTransaction()
                    .detach(fragment)
                    .attach(fragment)
                    .commit();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
