package com.example.myapplication.ui;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.example.myapplication.R;
import com.example.myapplication.model.Entry;
import com.example.myapplication.util.EntertainmentSpider;
import com.example.myapplication.util.EnvironmentSpider;
import com.example.myapplication.util.NewsSpider;
import com.example.myapplication.view.EntertainmentArticle;
import com.example.myapplication.view.EnvironmentArticle;
import com.example.myapplication.view.NewsArticle;
import com.example.myapplication.view.FeedState;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class SecondTabFragment extends Fragment {

    private static final String TAG = "SecondTabFragment";
    private int mIndx = 0;
    private ArrayList<Entry> mListLiveData = null;

    public void setmIndx(int mIndx) {
        this.mIndx = mIndx;
    }

    public void setmListLiveData(ArrayList<Entry> mListLiveData) {
        this.mListLiveData = mListLiveData;
    }

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

        FeedState feedState = ViewModelProviders.of(getActivity()).get(FeedState.class);

        NewsArticle newsArticle = ViewModelProviders.of(getActivity()).get(NewsArticle.class);
        newsArticle.init();
        NewsSpider newsSpider = new NewsSpider(newsArticle);

        EntertainmentArticle entertainmentArticle = ViewModelProviders.of(this).get(EntertainmentArticle.class);
        entertainmentArticle.init();
        EntertainmentSpider entertainmentSpider = new EntertainmentSpider(entertainmentArticle);

        EnvironmentArticle environmentArticle = ViewModelProviders.of(this).get(EnvironmentArticle.class);
        environmentArticle.init();
        EnvironmentSpider environmentSpider = new EnvironmentSpider(environmentArticle);




        feedState.getFeedType().observe(this, aBoolean -> {
            if(aBoolean)    {
                newsSpider.setState(true);
                newsSpider.start();
                environmentSpider.setState(false);
                entertainmentSpider.setState(false);

                newsArticle.getEntry().observe(SecondTabFragment.this, entry -> {
                    tvTitleMainFeed.setText(entry.getTitle());
                    tvDescriptionMainFeed.setText(entry.getDescription());
                    tvTitleSecondaryFeedView.setText("");
                    tvDescriptionSecondaryFeedView.setText("");
                });
            }
            if(!aBoolean)   {
                entertainmentSpider.setState(true);
                entertainmentSpider.start();

                entertainmentArticle.getEntry().observe(SecondTabFragment.this, entry -> {
                    tvTitleSecondaryFeedView.setText(entry.getTitle());
                    tvDescriptionSecondaryFeedView.setText(entry.getDescription());
                });

                newsSpider.setState(false);
                environmentSpider.setState(true);
                environmentSpider.start();

                environmentArticle.getEntry().observe(SecondTabFragment.this, entry -> {
                    tvTitleMainFeed.setText(entry.getTitle());
                    tvDescriptionMainFeed.setText(entry.getDescription());
                });
            }
        });


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
