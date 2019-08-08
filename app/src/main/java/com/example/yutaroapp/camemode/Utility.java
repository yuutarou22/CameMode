package com.example.yutaroapp.camemode;

import android.util.Log;

import java.util.List;

public class Utility {
    /* ログの出力(検索用) */
    public static void onCreateLog(String categoryRoleString, List<Boolean> freeDayArrayList,
                                   String whichChargeString, int spinnerSexInt, int spinnerAgeInt) {

        Log.d("onCreateLog","categoryRoleString: " + categoryRoleString);
        for (int i = 0; i < freeDayArrayList.size(); i++) {
            Log.d("onCreateLog", "freeDayArrayList: " + freeDayArrayList.get(i));
        }
//        for (Boolean bool : freeDayArrayList) {
//            Log.d("onCreateLog", "freeDayArrayList: " + bool);
//        }
        Log.d("onCreateLog", "whichChargeInt: " + whichChargeString);
        Log.d("onCreateLog", "spinnerSexInt: " + spinnerSexInt);
        Log.d("onCreateLog", "spinnerAgeInt: " + spinnerAgeInt);
    }

    /* ログの出力(登録用) */
    public static void onCreateLog(String categoryRoleString, String displayNameString, String passwordString, String categorySnsString,
                                   String snsUserNameString, List<Boolean> freeDayArrayList, String whichChargeString, int spinnerRegionInt,
                                   int spinnerSexInt, int spinnerAgeInt, String imaginationHopeString) {
        Log.d("onCreateLog", "categoryRoleString: " + categoryRoleString);
        Log.d("onCreateLog", "displayNameString: " + displayNameString);
        Log.d("onCreateLog", "categorySnsInt: " + categorySnsString);
        Log.d("onCreateLog", "snsUserNameString: " + snsUserNameString);
        for (boolean b : freeDayArrayList) {
            Log.d("onCreateLog", "freeDayArrayList: " + b);
        }
        Log.d("onCreateLog", "whichChargeString: " + whichChargeString);
        Log.d("onCreateLog", "spinnerRegionInt: " + spinnerRegionInt);
        Log.d("onCreateLog", "spinnerSexInt: " + spinnerSexInt);
        Log.d("onCreateLog", "spinnerAgeInt: " + spinnerAgeInt);
        Log.d("onCreateLog", "imaginationHopeString: " + imaginationHopeString);
    }

    /* バリデーションチェック */
    public boolean validationCheck() {
        return true;
    }
}