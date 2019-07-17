package com.example.yutaroapp.camemode;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.Toast;

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

    /* onActivityResultで用いるREQUEST_CODE */
    private static final int REQUEST_CODE = 1;

    /* FABの状態管理用列挙体 */
    enum FABState {
        OPEN,
        CLOSE
    }
    static FABState mFABState = FABState.CLOSE;

    /* UserInfoDataクラスのデータを格納するList */
    List<NCMBObject> userInfoDataList = new ArrayList<NCMBObject>();

    /* UserInfoDataクラスのデータを取得するクエリ */
    NCMBQuery<NCMBObject> query = new NCMBQuery<>("UserInfoData");

    @SuppressLint("ResourceType")
    private void setupViews() {
        mMainLayout = new MainLayout(getApplicationContext());
//        mMainLayout = (MainLayout) findViewById(R.layout.activity_main);
        mMainLayout.setUpViews(getWindow().getDecorView());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

        NCMB.initialize(this.getApplicationContext(), "7d6991b6ccf72cfbaf788b0f1315d58796c46f791165a8cb690238c217d2a6a7"
                , "8c04c78a810b705ef1b8fd6f832f74f6edbe0d99444c95bc1e6fe115a730def7");

        // updateDateフィールドの新しい順にデータ取得し、ListViewに出力
        query.addOrderByDescending("updateDate");
        query.setLimit(15);
        displayListView(query, userInfoDataList);
    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        setupViews();
//    }

    public static void startActivityforResult(Activity activity, Intent intent) {
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK || resultCode == RESULT_CANCELED) {
                    query.addOrderByDescending("updateDate");
                    query.setLimit(15);
                    displayListView(query, userInfoDataList);
                }
                break;
            default:
                break;
        }
    }

    private void displayListView(NCMBQuery query, final List<NCMBObject> userInfoDataList) {
        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> list, NCMBException e) {
                if (e != null) {
                    // エラー時
                    Toast.makeText(getApplicationContext(), "データ取得エラー", Toast.LENGTH_SHORT).show();
                } else {
                    // 成功時
                    // UserInfoDataList の確認
                    Toast.makeText(getApplicationContext(), "データ取得成功", Toast.LENGTH_SHORT).show();

                    for (NCMBObject obj : list) {
                        Log.d("MainActivity", "userInfoDataList DispName: " + obj.getString("DisplayName"));
                        userInfoDataList.add(obj);
                    }
                    display();
                }
            }
        });
    }

    private void display() {
        Resources res = getResources();
        RecyclerView UserListView = (RecyclerView) findViewById(R.id.user_info_list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        UserListView.addItemDecoration(itemDecoration);

        ArrayList<UserListItem> userListItems = new ArrayList<>();
        for (NCMBObject obj : userInfoDataList) {
            Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_gohan);
            UserListItem userItem = new UserListItem(bmp, obj.getString("DisplayName"));
            userListItems.add(userItem);
        }

        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(userListItems);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        UserListView.setHasFixedSize(true);
        UserListView.setLayoutManager(llm);

        UserListView.setAdapter(adapter);
    }
}