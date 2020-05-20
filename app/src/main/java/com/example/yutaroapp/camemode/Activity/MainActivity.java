package com.example.yutaroapp.camemode.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yutaroapp.camemode.Config;
import com.example.yutaroapp.camemode.Layout.MainLayout;
import com.example.yutaroapp.camemode.R;
import com.example.yutaroapp.camemode.UserListItem;
import com.example.yutaroapp.camemode.UserRecyclerViewAdapter;
import com.example.yutaroapp.camemode.Utility;
import com.nifcloud.mbaas.core.FindCallback;
import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;
import com.nifcloud.mbaas.core.NCMBQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /* MainLayout */
    private MainLayout mMainLayout;

    /* SwipeRefreshLayout */
    private SwipeRefreshLayout mSwipeRefreshLayout;

    /* UserInfoDataクラス（NCMBObject）のデータを格納するList */
    List<NCMBObject> userInfoDataList = new ArrayList<NCMBObject>();

    /* UserInfoDataクラス（NCMBObject）のデータを取得するクエリ */
    NCMBQuery<NCMBObject> query;

    /* ユーザ情報リスト */
    RecyclerView userListView;

    /* ユーザ情報リストのアダプタ */
    UserRecyclerViewAdapter adapter;

    private void setupViews() {
        mMainLayout = new MainLayout(this);
        mMainLayout.setUpViews(getWindow().getDecorView());
        mMainLayout.createTutorial(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        sp.getBoolean("isClosedTutorial", false);

        if (!sp.getBoolean("isClosedTutorial", false)) {
            Intent intent = new Intent(this, TutorialPagerActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_main);
            setupViews();
            userListView = (RecyclerView) findViewById(R.id.user_info_list);

            mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    createQueryToSearch();
                }
            });

            NCMB.initialize(this.getApplicationContext(), Config.getApplicationKey(), Config.getClientKey());
            createQueryToSearch();

        }
    }

    public static void startActivityforResult(Activity activity, Intent intent) {
        activity.startActivityForResult(intent, Utility.REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Utility.REQUEST_CODE:
                if (resultCode == RESULT_OK || resultCode == RESULT_CANCELED) {
                    createQueryToSearch();
                }
                break;
            default:
                break;
        }
    }

    /**
     * ユーザ情報取得クエリを作成し、更新する
     *
     * */
    public void createQueryToSearch() {
        // updateDateフィールドの新しい順にデータ取得し、ListViewに出力
        query = new NCMBQuery<>("UserInfoData");
        query.addOrderByDescending("updateDate");
        query.setLimit(15);
        applyUserInfoDataList(query, userInfoDataList);
    }

    /**
     * ユーザ情報を取得し、リストを更新する
     *
     * @param query            NCMBQuery 検索用のクエリー
     * @param userInfoDataList List<NCMBObject> 更新するリスト
     */
    private void applyUserInfoDataList(NCMBQuery query, final List<NCMBObject> userInfoDataList) {
        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> list, NCMBException e) {
                if (e != null) {
                    // エラー時
                    Toast.makeText(getApplicationContext(), R.string.data_get_error, Toast.LENGTH_SHORT).show();
                } else {
                    // 成功時
                    Toast.makeText(getApplicationContext(), R.string.data_get_success, Toast.LENGTH_SHORT).show();

                    // ユーザ情報をクリアし、新たに取得したユーザ情報をAddする
                    Utility.userInfoDataList.clear();
                    userInfoDataList.clear();
                    userInfoDataList.addAll(list);
                    Utility.userInfoDataList.addAll(list);

                    for (NCMBObject obj : list) {
                        //ToDo: 取得結果用ログ（キリのいいところで削除）
                        Log.d("MainActivity", "userInfoDataList DispName: " + obj.getString("DisplayName"));
                    }
                    applyedDisplayListView();
                }
            }
        });
    }

    /**
     * ユーザ情報を取得し、リストを更新する
     *
     * @param query            NCMBQuery 検索用のクエリー
     */
    private void addUserInfoDataList(NCMBQuery query) {
        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> list, NCMBException e) {
                if (e != null) {
                    // エラー時
                    Toast.makeText(getApplicationContext(), R.string.data_get_error, Toast.LENGTH_SHORT).show();
                } else {
                    // 成功時
                    Toast.makeText(getApplicationContext(), R.string.data_get_success, Toast.LENGTH_SHORT).show();

                    for (NCMBObject obj : list) {
                        Log.d("MainActivity", "userInfoDataList DispName: " + obj.getString("DisplayName"));
                        userInfoDataList.add(obj);
                        Utility.userInfoDataList.add(obj);
                    }
                    addedDisplayListView();
                }
            }
        });
    }

    /**
     * 取得したユーザ情報をリストビューに出力する
     */
    private void applyedDisplayListView() {
        final ArrayList<UserListItem> userListItems = new ArrayList<>();
        // 取得したユーザ情報をもとに、ユーザ情報アイテムを生成し、格納する
        for (NCMBObject obj : userInfoDataList) {
            UserListItem userItem = new UserListItem(obj.getString("DisplayName"), obj.getString("SNSUserName"), obj.getString("CategoryRole"), obj.getString("CategorySNS"), obj.getString("ImaginationHope"), obj.getInt("SpinnerAgeInt"), obj.getInt("SpinnerSex"));
            userListItems.add(userItem);
        }

        adapter = new UserRecyclerViewAdapter(userListItems, getSupportFragmentManager(), MainActivity.this);
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

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        userListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(-1)) {
                    /* リストの先頭に来た時の処理 */
                    android.util.Log.d("MainActivity","onScrolled Top");

                }

                if (!recyclerView.canScrollVertically(1)) {
                    /* リストの末尾に来た時の処理 */

                    // updateDateフィールドの新しい順にデータ取得し、ListViewに出力
                    query.addOrderByDescending("updateDate");
                    query.setLimit(15);
                    try {
                        Date date = Utility.getLastDate();
                        query.whereLessThanOrEqualTo("updateDate", date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    addUserInfoDataList(query);
                }
            }
        });
    }

    /**
     * 取得したユーザ情報をリストビューに出力する（下スクロール時用）
     */
    private void addedDisplayListView() {
        final ArrayList<UserListItem> userListItems = new ArrayList<>();
        // 取得したユーザ情報をもとに、ユーザ情報アイテムを生成し、格納する
        for (NCMBObject obj : userInfoDataList) {
            UserListItem userItem = new UserListItem(obj.getString("DisplayName"), obj.getString("SNSUserName"), obj.getString("CategoryRole"), obj.getString("CategorySNS"), obj.getString("ImaginationHope"), obj.getInt("SpinnerAgeInt"), obj.getInt("SpinnerSex"));
            userListItems.add(userItem);
        }
        adapter.refreshItemList(userListItems);
        adapter.setOnClickItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("display", "onClick!!!!!!!!!!!");
            }
        });
        adapter.notifyDataSetChanged();
    }
}