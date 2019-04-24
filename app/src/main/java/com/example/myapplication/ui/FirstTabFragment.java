package com.example.myapplication.ui;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.util.FeedLoader;
import com.example.myapplication.view.TypeOfFeed;

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

        TypeOfFeed typeOfFeed = ViewModelProviders.of(getActivity()).get(TypeOfFeed.class);
        typeOfFeed.getFeedType().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)    {
                    tvRssChannel.setText("News");
                }else {
                    tvRssChannel.setText("Entertainment & Environment");
                }

            }
        });

        return view;
    }
}
