package com.example.yutaroapp.camemode;

import android.util.Log;

import com.nifcloud.mbaas.core.NCMBObject;

import java.util.List;

public class SearchTask {
    private SearchResultListener listener;

    // interface設定
    public interface SearchResultListener {
        void onSuccess();
    }

    // listener
    public void setListener(SearchResultListener _listener) {
        this.listener = _listener;
    }

    public void taskStart(List<NCMBObject> list) {
        Utility.userInfoDataList.clear();
        for (NCMBObject obj : list) {
            Log.d("SearchTask", "userInfoDataList DispName: " + obj.getString("DisplayName"));
            Utility.userInfoDataList.add(obj);
        }

        // Util Listに格納されているか確認
        Log.d("SearchTask", "size(): " + Utility.userInfoDataList.size());

        if (listener != null) {
            // listener(SearchActivity) の onSuccess()を実行
            listener.onSuccess();
        }
    }
}
