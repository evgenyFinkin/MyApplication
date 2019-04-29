package com.example.myapplication.view;


import com.example.myapplication.util.TimeSeter;

import java.util.Calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimeFeed extends ViewModel {
    private static final String TAG = "TimeFeed";
    private MutableLiveData<String> currentTimeAndDite;
    public LiveData<String> getCurrentTimeAndDite(){return currentTimeAndDite;}

    public void setCurrentTimeAndDite(String currentTimeAndDite) {
        this.currentTimeAndDite.postValue(currentTimeAndDite);
    }

    public void init(){
        if(currentTimeAndDite != null) {
            return;
        }
        currentTimeAndDite = new MutableLiveData<>();
        currentTimeAndDite.setValue(
                java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())
        );
    }
}
