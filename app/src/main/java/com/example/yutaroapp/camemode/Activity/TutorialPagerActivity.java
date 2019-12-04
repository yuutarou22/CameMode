package com.example.yutaroapp.camemode.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        if (currentPage >= 0 && currentPage <4) {
            currentPage++;
        }
        pager.setCurrentItem(currentPage);
    }

    public void onClickBack(View view) {
        if (currentPage > 0 && currentPage <=4) {
            currentPage--;
        }
        pager.setCurrentItem(currentPage);
    }

    // 引数のViewは、TutorialPagerFragment3の「始めるボタン」
    public void onClickStartActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        SharedPreferences sp = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isClosedTutorial", true);
        editor.apply();

        startActivity(intent);
        finish();
    }
}
