package com.example.yutaroapp.camemode;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity {
  int freeDayArrayCount = 7;

  // レイアウト定義用
  RadioGroup categoryRole;
  EditText displayName;
  EditText password;
  RadioGroup categorySns;
  EditText snsUserName;
//  CheckBox freeDayMon;
//  CheckBox freeDayTue;
//  CheckBox freeDayWed;
//  CheckBox freeDayThu;
//  CheckBox freeDayFri;
//  CheckBox freeDaySat;
//  CheckBox freeDaySun;
  RadioGroup whichCharge;
  Spinner spinnerRegion;
  Spinner spinnerSex;
  Spinner spinnerAge;
  EditText imaginationHope;

  // データ送信用
  int categoryRoleInt;
  String displayNameString;
  String PasswordString;
  int categorySnsInt;
  String snsUserNameString;
//  boolean[] freeDayArray = new boolean[freeDayArrayCount];
  int whichChargeInt;
  int spinnerRegionInt;
  int spinnerSexInt;
  int spinnerAgeInt;
  String imaginationHopeString;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    // レイアウト定義
    onCreateView();

    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        categoryRoleInt = categoryRole.getCheckedRadioButtonId();
        displayNameString = displayName.getText().toString();
        PasswordString = password.getText().toString();
        categorySnsInt = categorySns.getCheckedRadioButtonId();
        snsUserNameString = snsUserName.getText().toString();
//        空き日用の配列に格納する処理
        whichChargeInt = whichCharge.getCheckedRadioButtonId();
        spinnerRegionInt = spinnerRegion.getSelectedItemPosition();
        spinnerSexInt = spinnerSex.getSelectedItemPosition();
        spinnerAgeInt = spinnerAge.getSelectedItemPosition();
        imaginationHopeString = imaginationHope.getText().toString();
      }
    });
  }

  protected void onCreateView() {
    categoryRole = (RadioGroup)findViewById(R.id.category_role);
    displayName = (EditText)findViewById(R.id.display_name);
    password = (EditText)findViewById(R.id.password);
    categorySns = (RadioGroup)findViewById(R.id.category_sns);
    snsUserName = (EditText)findViewById(R.id.sns_user_name);
//    freeDayMon = (CheckBox)findViewById(R.id.free_day_mon);
//    freeDayTue = (CheckBox)findViewById(R.id.free_day_tue);
//    freeDayWed = (CheckBox)findViewById(R.id.free_day_wed);
//    freeDayThu = (CheckBox)findViewById(R.id.free_day_thu);
//    freeDayFri = (CheckBox)findViewById(R.id.free_day_fri);
//    freeDaySat = (CheckBox)findViewById(R.id.free_day_sat);
//    freeDaySun = (CheckBox)findViewById(R.id.free_day_sun);
    whichCharge = (RadioGroup)findViewById(R.id.which_charge);
    spinnerRegion = (Spinner)findViewById(R.id.spinner_region);
    spinnerSex = (Spinner)findViewById(R.id.spinner_sex);
    spinnerAge = (Spinner)findViewById(R.id.spinner_age);
    imaginationHope = (EditText)findViewById(R.id.imagination_hope);
  }
}
