package com.example.yutaroapp.camemode;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nifcloud.mbaas.core.DoneCallback;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;

import java.util.ArrayList;
import java.util.List;

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
  FloatingActionButton fab; // 登録FABボタン

  // データ送信用
  int categoryRoleInt;
  RadioButton categoryRoleButton;
  String displayNameString;
  String passwordString;
  int categorySnsInt;
  RadioButton categorySnsButton;
  String snsUserNameString;
  List<Boolean> freeDayArrayList = new ArrayList<Boolean>();
  int whichChargeInt;
  RadioButton whichChargeButton;
  int spinnerRegionInt;
  int spinnerSexInt;
  int spinnerAgeInt;
  String imaginationHopeString;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add);
    onCreateView();

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        new AlertDialog.Builder(AddActivity.this).setTitle("登録しますか？")
                .setNeutralButton("はい", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    convertViewValue();
                    onCreateLog();
                    if (validationCheck(displayNameString, snsUserNameString)){
                      pushUserData();
                      freeDayArrayList.clear();
                      setResult(RESULT_OK);
                      finish();
                    }
                  }
                }).setPositiveButton("いいえ", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                  }
                }).show();
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
    fab = (FloatingActionButton) findViewById(R.id.fab);
  }

  protected void onCreateLog() {
    Log.d("TAG", "categoryRoleInt: " + categoryRoleButton.getText().toString());
    Log.d("TAG", "displayNameString: " + displayNameString);
    Log.d("TAG", "PasswordString: " + passwordString);
    Log.d("TAG", "categorySnsInt: " + categorySnsButton.getText().toString());
    Log.d("TAG", "snsUserNameString: " + snsUserNameString);
    for (boolean b : freeDayArrayList) {
      Log.d("TAG", "freeDayArrayList: " + b);
    }
    Log.d("TAG", "whichChargeInt: " + whichChargeButton.getText().toString());
    Log.d("TAG", "spinnerRegionInt: " + spinnerRegionInt);
    Log.d("TAG", "spinnerSexInt: " + spinnerSexInt);
    Log.d("TAG", "spinnerAgeInt: " + spinnerAgeInt);
    Log.d("TAG", "imaginationHopeString: " + imaginationHopeString);
  }

  protected void convertViewValue() {
    categoryRoleInt = categoryRole.getCheckedRadioButtonId();
    categoryRoleButton = findViewById(categoryRoleInt);
    displayNameString = displayName.getText().toString();
    passwordString = password.getText().toString();
    categorySnsInt = categorySns.getCheckedRadioButtonId();
    categorySnsButton = findViewById(categorySnsInt);
    snsUserNameString = snsUserName.getText().toString();
    for (int i = 0; i<freeDayArrayCount; i++) {
      freeDayArrayList.add(freeDay[i].isChecked());
    }
    whichChargeInt = whichCharge.getCheckedRadioButtonId();
    whichChargeButton = findViewById(whichChargeInt);
    spinnerRegionInt = spinnerRegion.getSelectedItemPosition();
    spinnerSexInt = spinnerSex.getSelectedItemPosition();
    spinnerAgeInt = spinnerAge.getSelectedItemPosition();
    imaginationHopeString = imaginationHope.getText().toString();
  }

  protected NCMBObject putUserInfo (NCMBObject userInfo) throws NCMBException {
    userInfo.put("CategoryRole", categoryRoleButton.getText().toString());
    userInfo.put("DisplayName", displayNameString);
    userInfo.put("Password", passwordString); // 扱いに対してもう少し考慮が必要。平文すぎる。
    userInfo.put("CategorySNS", categorySnsButton.getText().toString());
    userInfo.put("SNSUserName", snsUserNameString);
    userInfo.put("FreeDay", freeDayArrayList);
    userInfo.put("WhichCharge", whichChargeButton.getText().toString());
    userInfo.put("SpinnerRegionInt", spinnerRegionInt);
    userInfo.put("SpinnerSex", spinnerSexInt);
    userInfo.put("SpinnerAgeInt", spinnerAgeInt);
    userInfo.put("ImaginationHope", imaginationHopeString);
    
    return userInfo;
  }

  protected boolean validationCheck(String displayNameString, String snsUserNameString){
    if (displayNameString.isEmpty() || snsUserNameString.isEmpty()) {
      if (displayNameString.isEmpty()) {
        displayName.setError("表示名を入力してください。");
        displayName.setFocusable(true);
      }
      if (snsUserNameString.isEmpty()) {
        snsUserName.setError("SNSアカウント名を入力してください。");
        snsUserName.setFocusable(true);
      }
      return false;
    }
    return true;
  }

  protected void pushUserData() {
    NCMBObject saveUserInfoData = null;
    try {
      saveUserInfoData = putUserInfo(new NCMBObject("UserInfoData"));
    } catch (NCMBException e) {
      e.printStackTrace();
    }
    saveUserInfoData.saveInBackground(new DoneCallback() {
      @Override
      public void done(NCMBException e) {
        if (e != null) {
          // error
          Toast.makeText(getApplicationContext(), "登録エラー", Toast.LENGTH_SHORT).show();
        } else {
          // success
          Toast.makeText(getApplicationContext(), "登録成功", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }
}
