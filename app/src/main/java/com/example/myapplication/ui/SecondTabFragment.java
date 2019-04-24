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
import com.example.myapplication.view.TypeOfFeed;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

        TypeOfFeed typeOfFeed = ViewModelProviders.of(getActivity()).get(TypeOfFeed.class);

        if(mListLiveData != null)  {
            if(mListLiveData.get(mIndx).getTitle() != null )    {
                tvTitleMainFeed.setText((mListLiveData.get(mIndx).getTitle()));
            }else {
                tvTitleMainFeed.setText("No Title");
            }
        }else {
            tvTitleMainFeed.setText("Failed to load Title");
        }

        if(mListLiveData != null)   {
            if(mListLiveData.get(mIndx).getTitle() != null) {
                tvDescriptionMainFeed.setText((mListLiveData.get(mIndx).getDescription()));
            }else {
                tvDescriptionMainFeed.setText("No Description");
            }
        }else {
            tvDescriptionMainFeed.setText("Failed to load Description");
        }


        sbg.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                switch (position){
                    case 0:
                        typeOfFeed.setFeedType(true);
                        break;
                    case 1:
                        typeOfFeed.setFeedType(false);
                }
            }
        });

        sbg.getPosition();

        return view;
    }
}
