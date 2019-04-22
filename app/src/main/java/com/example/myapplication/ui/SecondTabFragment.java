package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.example.myapplication.R;
import com.example.myapplication.model.NewsArticle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

        sbg.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                switch (position){
                    case 0:
                        Toast.makeText(getActivity(),"News",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(),"Entertainment & Environment",Toast.LENGTH_SHORT).show();
                }
            }
        });

        sbg.getPosition();

        return view;
    }
}
