package com.example.yutaroapp.camemode;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
  RadioButton categoryRoleButton;
  String categoryRoleString;
  String displayNameString;
  String passwordString;
  RadioButton categorySnsButton;
  String categorySnsString;
  String snsUserNameString;
  List<Boolean> freeDayArrayList = new ArrayList<Boolean>();
  String whichChargeString;
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

    // 登録用FABを押下すると、ダイアログが出力される。
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        new AlertDialog.Builder(AddActivity.this).setTitle("登録しますか？")
                .setNeutralButton("はい", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    convertViewValue();

                    Utility.onCreateLog(categoryRoleString, displayNameString, passwordString, categorySnsString, snsUserNameString,
                            freeDayArrayList, whichChargeString, spinnerRegionInt, spinnerSexInt, spinnerAgeInt, imaginationHopeString);

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

  protected void convertViewValue() {
    categoryRoleButton = findViewById(categoryRole.getCheckedRadioButtonId());
    categoryRoleString = categoryRoleButton.getText().toString();
    displayNameString = displayName.getText().toString();
    passwordString = password.getText().toString();
    categorySnsButton = findViewById(categorySns.getCheckedRadioButtonId());
    categorySnsString = categorySnsButton.getText().toString();
    snsUserNameString = snsUserName.getText().toString();
    for (int i = 0; i<freeDayArrayCount; i++) {
      freeDayArrayList.add(freeDay[i].isChecked());
    }
    whichChargeButton = findViewById(whichCharge.getCheckedRadioButtonId());
    whichChargeString = whichChargeButton.getText().toString();
    spinnerRegionInt = spinnerRegion.getSelectedItemPosition();
    spinnerSexInt = spinnerSex.getSelectedItemPosition();
    spinnerAgeInt = spinnerAge.getSelectedItemPosition();
    imaginationHopeString = imaginationHope.getText().toString();
  }

  protected NCMBObject putUserInfo(NCMBObject userInfo) throws NCMBException {
    userInfo.put("CategoryRole", categoryRoleString);
    userInfo.put("DisplayName", displayNameString);
    userInfo.put("Password", passwordString); // 扱いに対してもう少し考慮が必要。平文すぎる。
    userInfo.put("CategorySNS", categorySnsString);
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
