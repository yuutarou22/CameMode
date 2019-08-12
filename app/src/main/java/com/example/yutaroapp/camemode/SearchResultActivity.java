package com.example.yutaroapp.camemode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nifcloud.mbaas.core.NCMBObject;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {
    /* SearchResultLayout */
    private SearchResultLayout mSearchResultLayout;

    /* UserInfoDataクラスを格納するList */
    Serializable userInfoDataList = new ArrayList<NCMBObject>();

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
//        Bundle bundle = intent.getExtras();
//        userInfoDataList = (List<NCMBObject>) bundle.get("LIST");
        userInfoDataList = intent.getSerializableExtra("LIST");

        Log.d("SearchResultActivity", "userInfoDataList size(): " + userInfoDataList.size());

        for (int i = 0; i<userInfoDataList.size(); i++) {
            Log.d("SearchResultActivity", "userInfoDataList DispName: " + userInfoDataList.get(i).getString("DisplayName"));
        }
    }
}
