package com.example.yutaroapp.camemode.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.yutaroapp.camemode.Layout.AddLayout;
import com.example.yutaroapp.camemode.R;
import com.nifcloud.mbaas.core.DoneCallback;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    /* AddLayout */
    private AddLayout mAddLayout;

    int freeDayArrayCount = 7;

    // データ送信用
    RadioButton categoryRoleButton;
    String categoryRoleString;
    public String displayNameString;
    String passwordString;
    RadioButton categorySnsButton;
    String categorySnsString;
    public String snsUserNameString;
    public List<Boolean> freeDayArrayList = new ArrayList<Boolean>();
    String whichChargeString;
    RadioButton whichChargeButton;
    int spinnerRegionInt;
    int spinnerSexInt;
    int spinnerAgeInt;
    String imaginationHopeString;

    private void setUpViews() {
        mAddLayout = new AddLayout(this);
        mAddLayout.setUpViews(getWindow().getDecorView());

        mAddLayout.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dialogStr = null;
                if (mAddLayout.password.getText().toString().length() == 0) {
                    dialogStr = "登録される情報は、今後削除・編集が出来ませんがよろしいですか？";
                } else {
                    dialogStr = "入力情報に誤りはありませんか？";
                }

                AlertDialog alertDialog = new AlertDialog.Builder(EditActivity.this).setTitle(dialogStr)
                        .setNeutralButton("はい", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                convertViewValue();
                                if (validationCheck(displayNameString, snsUserNameString, imaginationHopeString)) {
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
                        }).create();

                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        mAddLayout.createTutorial(this);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setUpViews();
    }

    public void convertViewValue() {
        // ToDo: ”mSearchLayout.”といちいちつけるのが冗長に感じる、気持ち悪い。
        categoryRoleButton = findViewById(mAddLayout.categoryRole.getCheckedRadioButtonId());
        categoryRoleString = categoryRoleButton.getText().toString();
        displayNameString = mAddLayout.displayName.getText().toString();
        passwordString = mAddLayout.password.getText().toString();
        categorySnsButton = findViewById(mAddLayout.categorySns.getCheckedRadioButtonId());
        categorySnsString = categorySnsButton.getText().toString();
        snsUserNameString = mAddLayout.snsUserName.getText().toString();
        for (int i = 0; i < freeDayArrayCount; i++) {
            freeDayArrayList.add(mAddLayout.freeDay[i].isChecked());
        }
        whichChargeButton = findViewById(mAddLayout.whichCharge.getCheckedRadioButtonId());
        whichChargeString = whichChargeButton.getText().toString();
        spinnerRegionInt = mAddLayout.spinnerRegion.getSelectedItemPosition();
        spinnerSexInt = mAddLayout.spinnerSex.getSelectedItemPosition();
        spinnerAgeInt = mAddLayout.spinnerAge.getSelectedItemPosition();
        imaginationHopeString = mAddLayout.imaginationHope.getText().toString();
    }

    protected NCMBObject putUserInfo(NCMBObject userInfo) throws NCMBException {
        userInfo.put("CategoryRole", categoryRoleString);
        userInfo.put("DisplayName", displayNameString);
        // ToDo: 扱いに対してもう少し考慮が必要。平文のままですよ。
        userInfo.put("Password", passwordString);
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

    public boolean validationCheck(String displayNameString, String snsUserNameString, String imaginationHopeString) {
        if (displayNameString.isEmpty() || snsUserNameString.isEmpty() || imaginationHopeString.isEmpty()) {
            if (displayNameString.isEmpty()) {
                mAddLayout.displayName.setError(getString(R.string.display_name_input_error));
                mAddLayout.displayName.setFocusable(true);
            }
            if (snsUserNameString.isEmpty()) {
                mAddLayout.snsUserName.setError(getString(R.string.sns_user_name_input_error));
                mAddLayout.snsUserName.setFocusable(true);
            }
            if (imaginationHopeString.isEmpty()) {
                mAddLayout.imaginationHope.setError(getString(R.string.imagination_input_error));
                mAddLayout.imaginationHope.setFocusable(true);
            }
            return false;
        }
        return true;
    }

    public void pushUserData() {
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
                    Toast.makeText(getApplicationContext(), R.string.data_put_error, Toast.LENGTH_SHORT).show();
                } else {
                    // success
                    Toast.makeText(getApplicationContext(), R.string.data_put_success, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
