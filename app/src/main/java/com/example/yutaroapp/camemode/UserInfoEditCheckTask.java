package com.example.yutaroapp.camemode;

import android.util.Log;

import com.nifcloud.mbaas.core.NCMBObject;

import java.util.List;

public class UserInfoEditCheckTask {
    private UserInfoEditCheckListener listener;

    // interface設定
    public interface UserInfoEditCheckListener {
        void onSuccess();
    }

    // listener
    public void setListener(UserInfoEditCheckListener _listener) {
        this.listener = _listener;
    }

    public void taskStart(List<NCMBObject> list, String inputText) {
        // 検索した項目のパスワードと、入力されたパスワードが同じであれば、trueにする。
        if (inputText.equals(list.get(0).getString("Password"))) {
            Utility.isValidPassword = true;
            Utility.editUserInfoData = list.get(0);
        }

        if (listener != null) {
            // listener(ListItemFragment) の onSuccess()を実行
            listener.onSuccess();
        }
    }
}
