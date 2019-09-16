package com.example.yutaroapp.camemode.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.yutaroapp.camemode.R;
import com.example.yutaroapp.camemode.Layout.SearchLayout;
import com.example.yutaroapp.camemode.SearchTask;
import com.example.yutaroapp.camemode.Utility;
import com.nifcloud.mbaas.core.FindCallback;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;
import com.nifcloud.mbaas.core.NCMBQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchTask.SearchResultListener {
    /* SearchLayout */
    private SearchLayout mSearchLayout;

    // データ送信用
    RadioButton categoryRoleButton;
    String categoryRoleString;
    List<Boolean> freeDayArrayList = new ArrayList<Boolean>();
    RadioButton whichChargeButton;
    String whichChargeString;
    int spinnerRegionInt;
    int spinnerSexInt;
    int spinnerAgeInt;

    // UserInfoDataクラスのデータを取得するクエリを作成
    NCMBQuery<NCMBObject> query = new NCMBQuery<>("UserInfoData");

    private void setUpViews() {
        mSearchLayout = new SearchLayout(this);
        mSearchLayout.setUpViews(getWindow().getDecorView());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setUpViews();

        mSearchLayout.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertViewValue();
                Utility.onCreateLog(categoryRoleString, freeDayArrayList, whichChargeString, spinnerSexInt, spinnerAgeInt);
                Toast.makeText(getApplicationContext(), "onClick", Toast.LENGTH_SHORT).show();
                searchUserData();
            }
        });
    }

    /**
     * クエリーに条件を指定し、検索をする。
     */
    protected void searchUserData() {

        createQuery();

        query.addOrderByDescending("updateDate");
        query.setLimit(15);
        // 検索処理タスクを生成
        final SearchTask searchTask = new SearchTask();
        searchTask.setListener(this);

        query.findInBackground(new FindCallback<NCMBObject>() {
            @Override
            public void done(List<NCMBObject> list, NCMBException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), "データ取得エラー", Toast.LENGTH_SHORT).show();
                } else {
                    // 検索処理を同期実行
                    searchTask.taskStart(list);
                }
            }
        });
    }

    /**
     * クエリに条件を指定する
     *   - 希望種別
     *   - 有償無償
     *   - 地域
     *   - 性別
     *   - 年代
     */
    private void createQuery() {
        query.whereEqualTo("CategoryRole", categoryRoleString);

        // ToDo: 空き日の検索条件を実装
//    freeDayArrayList

        query.whereEqualTo("WhichCharge", whichChargeString);

        setQueryRegion();
        setQuerySex();
        setQueryAge();
    }

    /**
     * 地域の検索条件を指定する
     * 「未選択」を選択した場合、全ての地域を検索
     */
    private void setQueryRegion() {
        if (spinnerRegionInt == 0) {
            ArrayList<NCMBQuery> queryArrayList = new ArrayList<NCMBQuery>();

            for (int i = 0; i < 9; i++) {
                queryArrayList.add(new NCMBQuery<>("UserInfoData"));
            }
            for (int i = 0; i < 9; i++) {
                queryArrayList.get(i).whereEqualTo("SpinnerRegionInt", i + 1);
            }
            query.or(queryArrayList);

        } else {
            query.whereEqualTo("SpinnerRegionInt", spinnerRegionInt - 1);
        }
    }

    /**
     * 性別の検索条件を指定する
     * 「未選択」を選択した場合、全ての性別を検索
     */
    private void setQuerySex() {
        if (spinnerSexInt == 0) {
            ArrayList<NCMBQuery> queryArrayList = new ArrayList<NCMBQuery>();

            for (int i = 0; i < 3; i++) {
                queryArrayList.add(new NCMBQuery<>("UserInfoData"));
            }
            for (int i = 0; i < 3; i++) {
                queryArrayList.get(i).whereEqualTo("SpinnerSex", i + 1);
            }
            query.or(queryArrayList);

        } else {
            query.whereEqualTo("SpinnerSex", spinnerSexInt - 1);
        }
    }

    /**
     * 年代の検索条件を指定する
     * 「未選択」を選択した場合、全ての年代を検索
     */
    private void setQueryAge() {
        if (spinnerAgeInt == 0) {
            ArrayList<NCMBQuery> queryArrayList = new ArrayList<NCMBQuery>();

            for (int i = 0; i < 3; i++) {
                queryArrayList.add(new NCMBQuery<>("UserInfoData"));
            }
            for (int i = 0; i < 3; i++) {
                queryArrayList.get(i).whereEqualTo("SpinnerAgeInt", i + 1);
            }
            query.or(queryArrayList);

        } else {
            query.whereEqualTo("SpinnerAgeInt", spinnerAgeInt - 1);
        }
    }

    /**
     * レイアウトから値を取得し、データ送信用変数に格納する
     */
    protected void convertViewValue() {
        // ToDo: ”mSearchLayout.”といちいちつけるのが冗長に感じる、気持ち悪い。
        categoryRoleButton = findViewById(mSearchLayout.categoryRole.getCheckedRadioButtonId());
        categoryRoleString = categoryRoleButton.getText().toString();
        for (int i = 0; i < mSearchLayout.freeDayArrayCount; i++) {
            freeDayArrayList.add(mSearchLayout.freeDay[i].isChecked());
        }
        whichChargeButton = findViewById(mSearchLayout.whichCharge.getCheckedRadioButtonId());
        whichChargeString = whichChargeButton.getText().toString();
        spinnerRegionInt = mSearchLayout.spinnerRegion.getSelectedItemPosition();
        spinnerSexInt = mSearchLayout.spinnerSex.getSelectedItemPosition();
        spinnerAgeInt = mSearchLayout.spinnerAge.getSelectedItemPosition();
    }

    /**
     * 検索（searchTask）が終わったら、検索結果Activityへ遷移する。
     */
    @Override
    public void onSuccess() {
        // ToDo: 空き日の検索条件を実装
        freeDayArrayList.clear();
        Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
        finish();
        startActivity(intent);
    }
}
