package com.example.yutaroapp.camemode;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.nifcloud.mbaas.core.NCMBObject;
import com.nifcloud.mbaas.core.NCMBQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
  int freeDayArrayCount = 7;

  // レイアウト定義用
  RadioGroup categoryRole;
  CheckBox[] freeDay = new CheckBox[freeDayArrayCount];
  RadioGroup whichCharge;
  Spinner spinnerRegion;
  Spinner spinnerSex;
  Spinner spinnerAge;
  Button searchButton;

  // データ送信用
  RadioButton categoryRoleButton;
  String categoryRoleString;
  List<Boolean> freeDayArrayList = new ArrayList<Boolean>();
  RadioButton whichChargeButton;
  String whichChargeString;
  int spinnerRegionInt;
  int spinnerSexInt;
  int spinnerAgeInt;

  // UserInfoDataクラスのデータを格納するListを作成
  List<NCMBObject> userInfoDataList = new ArrayList<NCMBObject>();
  // UserInfoDataクラスのデータを取得するクエリを作成
  NCMBQuery<NCMBObject> query = new NCMBQuery<>("UserInfoData");

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);
    onCreateView();

    // オブジェクトの検索(key というフィールドがvalueになっている)
//    query.whereEqualTo("key","value");

    // ボタンが押された時、入力されている値を取得しクエリーにぶっこむ
    searchButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        new AlertDialog.Builder(SearchActivity.this).setTitle("検索しますか？")
                .setNeutralButton("はい", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    convertViewValue();
                    // ToDo: Utilityクラスに共通処理をまとめる。
                    Utility.onCreateLog(categoryRoleString, freeDayArrayList, whichChargeString, spinnerSexInt, spinnerAgeInt);
                    Toast.makeText(getApplicationContext(),"onClick",Toast.LENGTH_SHORT).show();
//                    if (validationCheck(displayNameString, snsUserNameString)){
//                      pushUserData();
//                      freeDayArrayList.clear();
//                      setResult(RESULT_OK);
//                      finish();
//                    }
                    freeDayArrayList.clear();
                  }
                }).setPositiveButton("いいえ", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
          }
        }).show();
        // ToDo: 入力値のチェック
        /**
         *
         */

        // ToDo: 検索

      }
    });
  }

  protected void onCreateView() {
    categoryRole = (RadioGroup)findViewById(R.id.category_role);

    freeDay[0] = (CheckBox)findViewById(R.id.free_day_mon);
    freeDay[1] = (CheckBox)findViewById(R.id.free_day_tue);
    freeDay[2] = (CheckBox)findViewById(R.id.free_day_wed);
    freeDay[3] = (CheckBox)findViewById(R.id.free_day_thu);
    freeDay[4] = (CheckBox)findViewById(R.id.free_day_fri);
    freeDay[5] = (CheckBox)findViewById(R.id.free_day_sat);
    freeDay[6] = (CheckBox)findViewById(R.id.free_day_sun);

    whichCharge = (RadioGroup)findViewById(R.id.which_charge);
    spinnerRegion = (Spinner)findViewById(R.id.spinner_region);
    spinnerSex = (Spinner)findViewById(R.id.spinner_sex);
    spinnerAge = (Spinner)findViewById(R.id.spinner_age);
    searchButton = (Button)findViewById(R.id.search_button);
  }

  protected void convertViewValue() {
    categoryRoleButton = findViewById(categoryRole.getCheckedRadioButtonId());
    categoryRoleString = categoryRoleButton.getText().toString();
    for (int i = 0; i<freeDayArrayCount; i++) {
      freeDayArrayList.add(freeDay[i].isChecked());
    }
    whichChargeButton = findViewById(whichCharge.getCheckedRadioButtonId());
    whichChargeString = whichChargeButton.getText().toString();
    spinnerRegionInt = spinnerRegion.getSelectedItemPosition();
    spinnerSexInt = spinnerSex.getSelectedItemPosition();
    spinnerAgeInt = spinnerAge.getSelectedItemPosition();
  }
}
