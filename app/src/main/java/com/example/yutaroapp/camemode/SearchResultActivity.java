package com.example.yutaroapp.camemode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SearchResultActivity extends AppCompatActivity {
    /* SearchResultLayout */
    private SearchResultLayout mSearchResultLayout;

    private void setupViews() {
        mSearchResultLayout = new SearchResultLayout(this);
        mSearchResultLayout.setUpViews(getWindow().getDecorView());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        setupViews();

        Intent intent = getIntent();
        Log.d("onCreate", "intent: " + intent.toString());
        for (int i = 0; i<Utility.userInfoDataList.size(); i++) {
            Log.d("onCreate", "getStringArrayList " + Utility.userInfoDataList.get(i));
        }
    }
}
