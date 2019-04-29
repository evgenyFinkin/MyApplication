package com.example.myapplication.util;


import com.example.myapplication.view.EntertainmentFeed;
import com.example.myapplication.view.EnvironmentFeed;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class EnvironmentCrawler implements Runnable {
    private static final String TAG = "NewsCrawler";
    private EnvironmentFeed feed;
    private volatile boolean threadSuspended = false;
    private int indx = 0;
    private volatile boolean killThread = false;
    private Thread backgroundThread = new Thread(this);

    public boolean isThreadSuspended() {
        return threadSuspended;
    }

    public EnvironmentCrawler(EnvironmentFeed feed) {
        this.feed = feed;
    }

    public void start(){
        if(!backgroundThread.isAlive()){
            backgroundThread.start();
        }
        threadSuspended = false;
    }

    public void setThreadSuspended(boolean threadSuspended) {
        this.threadSuspended = threadSuspended;
    }

    public void setKillThread(boolean killThread) {
        this.killThread = killThread;
    }

    @Override
    public void run() {
        while (!killThread){
            if(!threadSuspended){
                if(feed != null){
                    indx = (
                            indx < Objects.requireNonNull(feed.getFeedList().getValue()).size() - 1
                    )
                            ? ++indx : 0;

                    feed.setEntry(indx);
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}