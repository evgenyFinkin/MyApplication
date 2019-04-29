package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.util.TimeSeter;
import com.example.myapplication.view.FeedState;
import com.example.myapplication.view.TimeFeed;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class FirstTabFragment extends Fragment {

    private static final String TAG = "FirstTabFragment";

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater
                             ,@Nullable ViewGroup container
                             ,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.first_tab_fragment
                                      ,container,false);
        TextView tvRssChannel = (TextView) view.findViewById(R.id.tvRssChannel);
        TextView tvDate = (TextView)  view.findViewById(R.id.tvDate);


        TimeFeed timeFeed = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(TimeFeed.class);
        timeFeed.init();
        TimeSeter timeSeter = new TimeSeter(timeFeed);
        timeSeter.start();
        timeFeed.getCurrentTimeAndDite().observe(FirstTabFragment.this, tvDate::setText);

        FeedState feedState = ViewModelProviders.of(getActivity()).get(FeedState.class);

        feedState.getFeedType().observe(this, aBoolean ->
                tvRssChannel.setText(aBoolean ? "News" : "Entertainment & Environment"));

        return view;
    }
}
