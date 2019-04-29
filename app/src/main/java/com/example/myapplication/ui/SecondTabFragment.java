package com.example.myapplication.ui;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.example.myapplication.R;
import com.example.myapplication.model.Entry;
import com.example.myapplication.util.EntertainmentCrawler;
import com.example.myapplication.util.EnvironmentCrawler;
import com.example.myapplication.util.NewsCrawler;
import com.example.myapplication.view.EntertainmentFeed;
import com.example.myapplication.view.EnvironmentFeed;
import com.example.myapplication.view.FeedState;
import com.example.myapplication.view.NewsFeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class SecondTabFragment extends Fragment {

    private static final String TAG = "SecondTabFragment";

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.second_tab_fragment, container,false);
        SegmentedButtonGroup sbg = (SegmentedButtonGroup) view.findViewById(R.id.segmentedbutton);
        LinearLayout MainFeed = (LinearLayout)view.findViewById(R.id.MainFeed);
        LinearLayout SecondaryFeedView = (LinearLayout)view.findViewById(R.id.SecondaryFeedView);

        TextView tvTitleMainFeed = (TextView) view.findViewById(R.id.tvTitleMainFeed);
        TextView tvDescriptionMainFeed = (TextView) view.findViewById(R.id.tvDescriptionMainFeed);
        TextView tvTitleSecondaryFeedView = (TextView) view.findViewById(R.id.tvTitleSecondaryFeedView);
        TextView tvDescriptionSecondaryFeedView = (TextView) view.findViewById(R.id.tvDescriptionSecondaryFeedView);

        NewsFeed newsFeed = ViewModelProviders.of(getActivity()).get(NewsFeed.class);
        newsFeed.init("businessNews");
        NewsCrawler newsCrawler = new NewsCrawler(newsFeed);

        EntertainmentFeed entertainmentFeed = ViewModelProviders.of(getActivity()).get(EntertainmentFeed.class);
        entertainmentFeed.init("entertainment");
        EntertainmentCrawler entertainmentCrawler = new EntertainmentCrawler(entertainmentFeed);

        EnvironmentFeed environmentFeed = ViewModelProviders.of(getActivity()).get(EnvironmentFeed.class);
        environmentFeed.init("environment");
        EnvironmentCrawler environmentCrawler = new EnvironmentCrawler(environmentFeed);


        FeedState feedState = ViewModelProviders.of(getActivity()).get(FeedState.class);
        feedState.getFeedType().observe(this, aBoolean -> {
            if(aBoolean)    {
                newsCrawler.start();
                entertainmentCrawler.setThreadSuspended(true);
                environmentCrawler.setThreadSuspended(true);

                newsFeed.getEntry().observe(SecondTabFragment.this, new Observer<Entry>() {
                    @Override
                    public void onChanged(Entry entry) {
                        tvTitleMainFeed.setText(entry.getTitle());
                        tvDescriptionMainFeed.setText(entry.getDescription());
                        tvTitleSecondaryFeedView.setText("");
                        tvDescriptionSecondaryFeedView.setText("");
                    }
                });

            }
            if(!aBoolean)   {
                newsCrawler.setThreadSuspended(true);
                entertainmentCrawler.start();
                environmentCrawler.start();

                entertainmentFeed.getEntry().observe(SecondTabFragment.this, new Observer<Entry>() {
                    @Override
                    public void onChanged(Entry entry) {
                        tvTitleMainFeed.setText(entry.getTitle());
                        tvDescriptionMainFeed.setText(entry.getDescription());
                    }
                });
                environmentFeed.getEntry().observe(SecondTabFragment.this, new Observer<Entry>() {
                    @Override
                    public void onChanged(Entry entry) {
                        tvTitleSecondaryFeedView.setText(entry.getTitle());
                        tvDescriptionSecondaryFeedView.setText(entry.getDescription());
                    }
                });

            }
        }
        );


        sbg.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                switch (position){
                    case 0:
                        feedState.setFeedType(true);
                        break;
                    case 1:
                        feedState.setFeedType(false);
                }
            }
        });

        sbg.getPosition();
        return view;
    }
}
