package com.example.yutaroapp.camemode.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.yutaroapp.camemode.R;
import com.example.yutaroapp.camemode.Layout.SearchResultLayout;
import com.example.yutaroapp.camemode.UserListItem;
import com.example.yutaroapp.camemode.UserRecyclerViewAdapter;
import com.example.yutaroapp.camemode.Utility;
import com.nifcloud.mbaas.core.NCMBObject;

import java.util.ArrayList;

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
    }

    // ToDo: Listへの出力はonResumeで問題ないか調査
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Log.d("onCreate", "intent: " + intent.toString());
        for (int i = 0; i < Utility.userInfoDataList.size(); i++) {
            Log.d("onCreate", "getStringArrayList " + Utility.userInfoDataList.get(i));
        }
        displayListView();
    }

    /**
     * 取得したユーザ情報をリストビューに出力する
     */
    private void displayListView() {
        final RecyclerView userListView = (RecyclerView) findViewById(R.id.user_info_list);

        final ArrayList<UserListItem> userListItems = new ArrayList<>();
        // 取得したユーザ情報をもとに、ユーザ情報アイテムを生成し、格納する
        for (NCMBObject obj : Utility.userInfoDataList) {
            UserListItem userItem = new UserListItem(obj.getString("DisplayName"), obj.getString("CategoryRole"), obj.getString("ImaginationHope"));
            userListItems.add(userItem);
        }

        final UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(userListItems, getSupportFragmentManager());
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        userListView.setHasFixedSize(true);
        userListView.setLayoutManager(llm);

        userListView.setAdapter(adapter);
        adapter.setOnClickItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("display", "onClick!!!!!!!!!!!");
            }
        });
    }
}
