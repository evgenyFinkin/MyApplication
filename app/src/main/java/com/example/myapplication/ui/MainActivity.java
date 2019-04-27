package com.example.myapplication.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.util.Log;
import com.example.myapplication.R;
import com.example.myapplication.util.EntertainmentSpider;
import com.example.myapplication.util.EnvironmentSpider;
import com.example.myapplication.util.NewsSpider;
import com.example.myapplication.view.EntertainmentArticle;
import com.example.myapplication.view.EnvironmentArticle;
import com.example.myapplication.view.NewsArticle;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    FirstTabFragment mFirstTabFragment = new FirstTabFragment();
    SecondTabFragment mSecondTabFragment = new SecondTabFragment();


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


    }

    private void setupViewPager(ViewPager viewPager)    {
        SectionPageAdapter sectionPageAdapter =
            new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragment(mFirstTabFragment, "First Tab");
        sectionPageAdapter.addFragment(mSecondTabFragment, "Second Tab");
        viewPager.setAdapter(sectionPageAdapter);
    }
}

