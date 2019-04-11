package com.example.myapplication;

import android.nfc.Tag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstTabFragment extends Fragment {

    private static final String TAG = "FirstTabFragment";

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater
                             ,@Nullable ViewGroup container
                             ,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.first_tab_fragment
                                      ,container,false);
        return view;
    }
}
