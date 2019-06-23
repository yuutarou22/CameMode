package com.example.yutaroapp.camemode;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nifcloud.mbaas.core.FindCallback;
import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;
import com.nifcloud.mbaas.core.DoneCallback;
import com.nifcloud.mbaas.core.NCMBQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  // FABの状態管理用列挙体
  enum FABState {
    OPEN,
    CLOSE
  }
  FABState mFABState = FABState.CLOSE;

  // UserInfoDataクラスのデータを格納するListを作成
  List<NCMBObject> userInfoDataList = new ArrayList<NCMBObject>();

  // UserInfoDataクラスのデータを取得するクエリを作成
  NCMBQuery<NCMBObject> query = new NCMBQuery<>("UserInfoData");
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    NCMB.initialize(this.getApplicationContext(),"7d6991b6ccf72cfbaf788b0f1315d58796c46f791165a8cb690238c217d2a6a7"
            ,"8c04c78a810b705ef1b8fd6f832f74f6edbe0d99444c95bc1e6fe115a730def7");

    // FAB処理
    FloatingActionButton fabMenuButton = (FloatingActionButton) findViewById(R.id.fab);
    fabMenuButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int iconWhile = 350;
            if (mFABState == mFABState.CLOSE) {
                fabOpen(iconWhile);
            } else {
                fabClose();
            }
        }
    });

    FloatingActionButton fabAddButton = (FloatingActionButton) findViewById(R.id.fab_add);
    fabAddButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fabClose();
            Intent intent = new Intent(getApplication(), AddActivity.class);
            startActivity(intent);
        }
    });

    FloatingActionButton fabSearchButton = (FloatingActionButton) findViewById(R.id.fab_search);
    fabSearchButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fabClose();
            Intent intent = new Intent(getApplication(), SearchActivity.class);
            startActivity(intent);
        }
    });

    // updateDateフィールドの降順（新しい順）で取得し、ユーザ情報をListViewに出力する
    query.addOrderByDescending("updateDate");
    query.setLimit(15);
    displayListView(query, userInfoDataList);
  }

  // onRestartは、Activityが再度呼び出された時に実行するライフサイクルメソッド
  protected void onRestart() {
      super.onRestart();
      query.addOrderByDescending("updateDate");
      query.setLimit(15);
      displayListView(query, userInfoDataList);
  }

  private void fabOpen(int iconWhile) {
    LinearLayout mFabSearchLayout = findViewById(R.id.fab_search_layout);
    LinearLayout mFabAddLayout = findViewById(R.id.fab_add_layout);
    FloatingActionButton mFab = findViewById(R.id.fab);
    View mFabBackground = findViewById(R.id.fab_background);

    mFabSearchLayout.setVisibility(View.VISIBLE);
    ObjectAnimator animator = ObjectAnimator.ofFloat(mFabSearchLayout, "translationY", -iconWhile);
    animator.setDuration(250);
    animator.start();

    mFabAddLayout.setVisibility(View.VISIBLE);
    animator = ObjectAnimator.ofFloat(mFabAddLayout, "translationY", -iconWhile * 2);
    animator.setDuration(250);
    animator.start();

    mFab.setImageResource(R.drawable.ic_clear_button);
    animator = ObjectAnimator.ofFloat(mFab, "rotation", 270);
    animator.setDuration(250);
    animator.start();

    mFABState = FABState.OPEN;
    mFabBackground.animate().alpha(0.8f).setDuration(300);
  }

  private void fabClose() {
    LinearLayout mFabSearchLayout = findViewById(R.id.fab_search_layout);
    LinearLayout mFabAddLayout = findViewById(R.id.fab_add_layout);
    FloatingActionButton mFab = findViewById(R.id.fab);
    View mFabBackground = findViewById(R.id.fab_background);

    ObjectAnimator animator = ObjectAnimator.ofFloat(mFabSearchLayout, "translationY", 0);
    animator.setDuration(200);
    animator.start();
    mFabSearchLayout.setVisibility(View.GONE);

    animator = ObjectAnimator.ofFloat(mFabAddLayout, "translationY", 0);
    animator.setDuration(200);
    animator.start();
    mFabAddLayout.setVisibility(View.GONE);

    mFab.setImageResource(R.drawable.ic_menu_button);
    animator = ObjectAnimator.ofFloat(mFab, "rotation", 0);
    animator.setDuration(200);
    animator.start();

    mFABState = FABState.CLOSE;
    mFabBackground.animate().alpha(0f).setDuration(200);
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