package com.example.myapplication.ui;



import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.view.NewsFeed;
import com.example.myapplication.model.RSS;
import com.example.myapplication.server.FeedAPI;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionPageAdapter mSectionPageAdapter;



    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting");

        //Lets load our content
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(mViewPager);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager)    {
        SectionPageAdapter sectionPageAdapter =
            new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragment(new FirstTabFragment(), "First Tab");
        sectionPageAdapter.addFragment(new SecondTabFragment(), "Second Tab");
        viewPager.setAdapter(sectionPageAdapter);
    }

}
