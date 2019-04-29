package com.example.myapplication.util;


import android.os.Handler;
import android.os.Looper;

public class Spider extends Thread {
    private static final String TAG = "Spider";
    public Handler handler;
    public Looper looper;

    @Override
    public void run() {
        Looper.prepare();
        looper = Looper.myLooper();
        handler = new Handler();
        Looper.loop();
    }
}
