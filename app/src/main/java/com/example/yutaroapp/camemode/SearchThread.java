package com.example.yutaroapp.camemode;

import android.util.Log;
import android.widget.Toast;

import com.nifcloud.mbaas.core.NCMBObject;

import java.util.List;

/**
 * 検索結果をUtility.userInfoDataListにAddする処理を、同期処理にするためのスレッド
 */
public class SearchThread extends Thread {
    private List<NCMBObject> list;

    public SearchThread(String name, List<NCMBObject> list) {
        super(name);
        this.list = list;
    }

    synchronized public void run() {
        Utility.userInfoDataList.clear();
        for (NCMBObject obj : list) {
            Log.d("SearchActivity", "userInfoDataList DispName: " + obj.getString("DisplayName"));
            Utility.userInfoDataList.add(obj);
        }
    }
}
