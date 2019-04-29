package com.example.myapplication.util;

import com.example.myapplication.view.TimeFeed;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
//Chronometer was taken :(

public class TimeSeter implements Runnable {
    private static final String TAG = "TimeSeter";
    private volatile boolean killThread = false;
    private Thread backgroundThread = new Thread(this);
    private TimeFeed timeFeed;

    public TimeSeter(TimeFeed timeFeed) {
        this.timeFeed = timeFeed;
    }

    public void start(){
        if(!backgroundThread.isAlive()){
            backgroundThread.start();
        }
    }

    @Override
    public void run() {
        while (!killThread){
            if(timeFeed != null){
                timeFeed.setCurrentTimeAndDite(
                        java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())
                );
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
