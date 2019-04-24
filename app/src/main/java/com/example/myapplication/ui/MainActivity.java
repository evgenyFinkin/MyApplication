package com.example.myapplication.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.example.myapplication.R;
import com.example.myapplication.util.FeedLoader;
import com.example.myapplication.util.FeedScroller;
import com.example.myapplication.view.NewsArticle;
import com.example.myapplication.view.TypeOfFeed;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    FirstTabFragment mfirstTabFragment = new FirstTabFragment();
    SecondTabFragment mSecondTabFragment = new SecondTabFragment();
    private FeedScroller mFeedScroller;
    private FeedLoader mFeedLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting");

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(mViewPager);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

        NewsArticle newsArticle = ViewModelProviders.of(this).get(NewsArticle.class);
        newsArticle.init();

        newsArticle.getNewsArticles().observe(this, entries -> {
            mSecondTabFragment.setmListLiveData(entries);
            mFeedScroller = new FeedScroller(mSecondTabFragment, entries.size(), 0, entries);
            mFeedScroller.start();
        });

        TypeOfFeed typeOfFeed = ViewModelProviders.of(this).get(TypeOfFeed.class);
        typeOfFeed.init();
        typeOfFeed.getFeedType().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mFeedLoader = new FeedLoader();
            }
        });

    }

    private void setupViewPager(ViewPager viewPager)    {
        SectionPageAdapter sectionPageAdapter =
            new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragment(mfirstTabFragment, "First Tab");
        sectionPageAdapter.addFragment(mSecondTabFragment, "Second Tab");
        viewPager.setAdapter(sectionPageAdapter);
    }
}

