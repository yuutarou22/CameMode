package com.example.yutaroapp.camemode.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /* MainLayout */
    private MainLayout mMainLayout;

    /* UserInfoDataクラスのデータを格納するList */
    List<NCMBObject> userInfoDataList = new ArrayList<NCMBObject>();

    /* UserInfoDataクラスのデータを取得するクエリ */
    NCMBQuery<NCMBObject> query = new NCMBQuery<>("UserInfoData");

    private void setupViews() {
        mMainLayout = new MainLayout(this);
        mMainLayout.setUpViews(getWindow().getDecorView());
        mMainLayout.createTutorial(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

        NCMB.initialize(this.getApplicationContext(), Config.getApplicationKey(), Config.getClientKey());

        // updateDateフィールドの新しい順にデータ取得し、ListViewに出力
        query.addOrderByDescending("updateDate");
        query.setLimit(15);
        applyUserInfoDataList(query, userInfoDataList);
    }

    public static void startActivityforResult(Activity activity, Intent intent) {
        activity.startActivityForResult(intent, Utility.REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Utility.REQUEST_CODE:
                if (resultCode == RESULT_OK || resultCode == RESULT_CANCELED) {
                    query.addOrderByDescending("updateDate");
                    query.setLimit(15);
                    applyUserInfoDataList(query, userInfoDataList);
                }
                break;
            default:
                break;
        }
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
                    for (NCMBObject obj : list) {
                        Log.d("MainActivity", "userInfoDataList DispName: " + obj.getString("DisplayName"));
                        userInfoDataList.add(obj);
                        Utility.userInfoDataList.add(obj);
                    }
                    displayListView();
                }
            }
        });
    }

    /**
     * 取得したユーザ情報をリストビューに出力する
     */
    private void displayListView() {
        final RecyclerView userListView = (RecyclerView) findViewById(R.id.user_info_list);

        final ArrayList<UserListItem> userListItems = new ArrayList<>();
        // 取得したユーザ情報をもとに、ユーザ情報アイテムを生成し、格納する
        for (NCMBObject obj : userInfoDataList) {
            UserListItem userItem = new UserListItem(obj.getString("DisplayName"), obj.getString("SNSUserName"), obj.getString("CategoryRole"), obj.getString("CategorySNS"), obj.getString("ImaginationHope"), obj.getInt("SpinnerAgeInt"), obj.getInt("SpinnerSex"));
            userListItems.add(userItem);
        }

        final UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(userListItems, getSupportFragmentManager(), MainActivity.this);
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