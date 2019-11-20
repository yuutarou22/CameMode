package com.example.yutaroapp.camemode.Activity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yutaroapp.camemode.R;
import com.example.yutaroapp.camemode.TutorialViewPagerAdapter;

public class TutorialPagerActivity extends AppCompatActivity {

    private ViewPager pager;
    private FragmentPagerAdapter adapter;
    private int currentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_pager);

        pager = (ViewPager)findViewById(R.id.tutorial_pager);

        adapter = new TutorialViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        currentPage = 0;
    }

    public void onClickNext(View view) {
        currentPage++;
        pager.setCurrentItem(currentPage);
    }

    public void onClickGoToTop(View view) {
        currentPage = 0;
        pager.setCurrentItem(currentPage);
    }
}
