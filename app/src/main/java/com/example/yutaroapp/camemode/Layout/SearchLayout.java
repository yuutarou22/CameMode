package com.example.yutaroapp.camemode.Layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.yutaroapp.camemode.R;


public class SearchLayout extends RelativeLayout {

    public int freeDayArrayCount = 7;
    // レイアウト定義用
    public RadioGroup categoryRole;
    public CheckBox[] freeDay = new CheckBox[freeDayArrayCount];
    public RadioGroup whichCharge;
    public Spinner spinnerRegion;
    public Spinner spinnerSex;
    public Spinner spinnerAge;
    public Button searchButton;

    /**
     * コンテキスト.
     */
    private Context mContext;

    /* コンストラクタ（理由もわからずとりあえず3つ設けている） */
    public SearchLayout(Context context) {
        super(context);
        mContext = context;
    }

    public SearchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public SearchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    /**
     * レイアウトの初期化
     * @param view
     */
    public void setUpViews(View view) {
        categoryRole = (RadioGroup) view.findViewById(R.id.category_role);

        freeDay[0] = (CheckBox) view.findViewById(R.id.free_day_mon);
        freeDay[1] = (CheckBox) view.findViewById(R.id.free_day_tue);
        freeDay[2] = (CheckBox) view.findViewById(R.id.free_day_wed);
        freeDay[3] = (CheckBox) view.findViewById(R.id.free_day_thu);
        freeDay[4] = (CheckBox) view.findViewById(R.id.free_day_fri);
        freeDay[5] = (CheckBox) view.findViewById(R.id.free_day_sat);
        freeDay[6] = (CheckBox) view.findViewById(R.id.free_day_sun);

        whichCharge = (RadioGroup) view.findViewById(R.id.which_charge);
        spinnerRegion = (Spinner) view.findViewById(R.id.spinner_region);
        spinnerSex = (Spinner) view.findViewById(R.id.spinner_sex);
        spinnerAge = (Spinner) view.findViewById(R.id.spinner_age);
        searchButton = (Button) view.findViewById(R.id.search_button);

    }
}