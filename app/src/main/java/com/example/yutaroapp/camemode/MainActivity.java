package com.example.yutaroapp.camemode;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;
import com.nifcloud.mbaas.core.DoneCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  // FABの状態管理用列挙体
  enum FABState {
    OPEN,
    CLOSE
  }
  FABState mFABState = FABState.CLOSE;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    NCMB.initialize(this.getApplicationContext(),"7d6991b6ccf72cfbaf788b0f1315d58796c46f791165a8cb690238c217d2a6a7"
            ,"8c04c78a810b705ef1b8fd6f832f74f6edbe0d99444c95bc1e6fe115a730def7");

    // mBaaSのデータストアに保存先の「TestClass」というクラスを作成し、
    // 「message」というフィールドへ「Hello, NCMB!」というメッセージ（文字列）を保存するもの
    NCMBObject obj = new NCMBObject("TestClass");

    // オブジェクトに値を設定
    try {
      obj.put("message", "Hello, NCMB!");
    } catch (NCMBException e) {
      e.printStackTrace();
    }

    // 設定したオブジェクトをデータストアへ登録
    obj.saveInBackground(new DoneCallback() {
      @Override
      public void done(NCMBException e) {
        if (e != null) {

        } else {

        }
      }
    });

    // リスト要素を設定
    ListView UserListView = (ListView)findViewById(R.id.user_info_list);
    ArrayList<UserListItem> userListItems = new ArrayList<>();

    Resources res = getResources();
    for (int i = 0; i < 15; i++) {
      Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_gohan);
      UserListItem userItem = new UserListItem(bmp, "ねこにゃん" + String.valueOf(i+1));
      userListItems.add(userItem);
    }

    // リスト表示
    UserListAdapter adapter = new UserListAdapter(this, R.layout.user_info_list_item, userListItems);
    UserListView.setAdapter(adapter);

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
        Intent intent = new Intent(getApplication(), AddActivity.class);
        startActivity(intent);
      }
    });

    FloatingActionButton fabSearchButton = (FloatingActionButton) findViewById(R.id.fab_search);
    fabSearchButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplication(), SearchActivity.class);
        startActivity(intent);
      }
    });
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
}
