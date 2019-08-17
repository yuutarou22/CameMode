package com.example.yutaroapp.camemode.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
        Resources res = getResources();
        RecyclerView userListView = (RecyclerView) findViewById(R.id.user_info_list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        userListView.addItemDecoration(itemDecoration);

        ArrayList<UserListItem> userListItems = new ArrayList<>();
        // 取得したユーザ情報をもとに、アイテムに格納
        for (NCMBObject obj : Utility.userInfoDataList) {
            Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_gohan);
            // ユーザ情報アイテムを生成
            UserListItem userItem = new UserListItem(bmp, obj.getString("DisplayName"), obj.getString("CategoryRole"));
            userListItems.add(userItem);
        }

        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(userListItems);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        userListView.setHasFixedSize(true);
        userListView.setLayoutManager(llm);

        userListView.setAdapter(adapter);
    }
}
