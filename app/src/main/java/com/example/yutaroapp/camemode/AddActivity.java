package com.example.yutaroapp.camemode;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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
  CheckBox[] freeDay = new CheckBox[freeDayArrayCount];
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
  boolean[] freeDayArray = new boolean[freeDayArrayCount];
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
        RadioButton categoryRoleButton = findViewById(categoryRoleInt);
        displayNameString = displayName.getText().toString();
        PasswordString = password.getText().toString();
        categorySnsInt = categorySns.getCheckedRadioButtonId();
        RadioButton categorySnsButton = findViewById(categorySnsInt);
        snsUserNameString = snsUserName.getText().toString();
        for (int i = 0; i<freeDayArrayCount; i++) {
          freeDayArray[i] = freeDay[i].isChecked();
        }
        whichChargeInt = whichCharge.getCheckedRadioButtonId();
        RadioButton whichChargeButton = findViewById(whichChargeInt);
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
    imaginationHope = (EditText)findViewById(R.id.imagination_hope);
  }
}
