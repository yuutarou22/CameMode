package com.example.yutaroapp.camemode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.nifcloud.mbaas.core.FindCallback;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;
import com.nifcloud.mbaas.core.NCMBQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
  /* SearchLayout */
  private SearchLayout mSearchLayout;

  // データ送信用
  RadioButton categoryRoleButton;
  String categoryRoleString;
  List<Boolean> freeDayArrayList = new ArrayList<Boolean>();
  RadioButton whichChargeButton;
  String whichChargeString;
  int spinnerRegionInt;
  int spinnerSexInt;
  int spinnerAgeInt;

  // UserInfoDataクラスのデータを取得するクエリを作成
  NCMBQuery<NCMBObject> query = new NCMBQuery<>("UserInfoData");

    private void setUpViews() {
    mSearchLayout = new SearchLayout(this);
    mSearchLayout.setUpViews(getWindow().getDecorView());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_search);
      setUpViews();

      mSearchLayout.searchButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              convertViewValue();
              Utility.onCreateLog(categoryRoleString, freeDayArrayList, whichChargeString, spinnerSexInt, spinnerAgeInt);
              Toast.makeText(getApplicationContext(), "onClick", Toast.LENGTH_SHORT).show();
              searchUserData(query);
              freeDayArrayList.clear();
              // 検索結果（SearchResultActivity）へ遷移する
              Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
              finish();
              startActivity(intent);
          }
      });
  }

  protected void searchUserData(NCMBQuery query) {
      // クエリー作成
      query.whereEqualTo("CategoryRole", categoryRoleString);
      // ToDo: 空き日の検索条件を実装
//    freeDayArrayList
      query.whereEqualTo("WhichCharge", whichChargeString);
      query.whereEqualTo("SpinnerRegionInt", spinnerRegionInt);
      query.whereEqualTo("SpinnerSex", spinnerSexInt);
      query.whereEqualTo("SpinnerAgeInt", spinnerAgeInt);

      query.addOrderByDescending("updateDate");
      query.setLimit(15);
      query.findInBackground(new FindCallback<NCMBObject>() {
          @Override
          public void done(List<NCMBObject> list, NCMBException e) {
              if (e != null) {
                  Toast.makeText(getApplicationContext(), "データ取得エラー", Toast.LENGTH_SHORT).show();
              } else {
                  SearchThread searchThread = new SearchThread("search", list);
                  searchThread.start();
              }
          }
      });

      // Util Listに格納されているか確認
      Log.d("SearchActivity", "size(): " + Utility.userInfoDataList.size());
      for (NCMBObject obj : Utility.userInfoDataList) {
          Log.d("SearchActivity", "item: " + obj.getString("DisplayName"));
      }
  }

  /**
   * レイアウトから値を取得し、データ送信用変数に格納する
   */
  protected void convertViewValue() {
      // ToDo: ”mSearchLayout.”といちいちつけるのが冗長に感じる、気持ち悪い。
      categoryRoleButton = findViewById(mSearchLayout.categoryRole.getCheckedRadioButtonId());
      categoryRoleString = categoryRoleButton.getText().toString();
      for (int i = 0; i<mSearchLayout.freeDayArrayCount; i++) {
          freeDayArrayList.add(mSearchLayout.freeDay[i].isChecked());
      }
      whichChargeButton = findViewById(mSearchLayout.whichCharge.getCheckedRadioButtonId());
      whichChargeString = whichChargeButton.getText().toString();
      spinnerRegionInt = mSearchLayout.spinnerRegion.getSelectedItemPosition();
      spinnerSexInt = mSearchLayout.spinnerSex.getSelectedItemPosition();
      spinnerAgeInt = mSearchLayout.spinnerAge.getSelectedItemPosition();
  }
}
