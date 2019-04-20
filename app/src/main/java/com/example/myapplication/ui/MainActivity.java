package com.example.myapplication.ui;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Item;
import com.example.myapplication.model.NewsFeed;
import com.example.myapplication.model.RSS;
import com.example.myapplication.pojo.RSSContent;
import com.example.myapplication.server.FeedAPI;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionPageAdapter mSectionPageAdapter;
    NewsFeed newsFeed = new NewsFeed();


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



        Retrofit retrofit = new Retrofit.Builder().baseUrl(FeedAPI.BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();
        FeedAPI feedAPI = retrofit.create(FeedAPI.class);
        Call<RSS> call = feedAPI.getRSS();



        call.enqueue(new Callback<RSS>() {
            @Override
            public void onResponse(Call<RSS> rss, Response<RSS> response) {
                assert response.body() != null;
                Log.d(TAG,"onResponse, RSS feed: " + response.body().getChannel().getItem().toString());
                Log.d(TAG,"onResponse, Server response:" + response.toString());
                newsFeed.setNewsFeed(response.body());

            }

            @Override
            public void onFailure(Call<RSS> rss, Throwable t) {
                Log.e(TAG,"onFailure: Was unable to retrieve RSS"
                        + t.getMessage());
                Toast.makeText(MainActivity.this,
                        "Was unable to retrieve RSS",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager)    {
        SectionPageAdapter sectionPageAdapter =
            new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragment(new FirstTabFragment(), "First Tab");
        sectionPageAdapter.addFragment(new SecondTabFragment(), "Second Tab");
        viewPager.setAdapter(sectionPageAdapter);
    }

}
