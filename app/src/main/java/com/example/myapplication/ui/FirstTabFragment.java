package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.view.FeedState;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

        FeedState feedState = ViewModelProviders.of(getActivity()).get(FeedState.class);
        feedState.getFeedType().observe(this, aBoolean ->
                tvRssChannel.setText(aBoolean ? "News" : "Entertainment & Environment"));

        return view;
    }
}
