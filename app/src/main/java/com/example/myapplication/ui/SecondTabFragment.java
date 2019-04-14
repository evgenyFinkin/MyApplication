package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class SecondTabFragment extends Fragment {

    private static final String TAG = "SecondTabFragment";

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.second_tab_fragment, container,false);

        SegmentedButtonGroup sbg = (SegmentedButtonGroup) view.findViewById(R.id.segmentedbutton);
        View vMainFeed = view.findViewById(R.id.vMainFeed);
        View vSecondaryFeedView = view.findViewById(R.id.vSecondaryFeedView);

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
