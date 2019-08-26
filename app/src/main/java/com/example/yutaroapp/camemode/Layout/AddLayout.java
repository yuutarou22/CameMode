package com.example.yutaroapp.camemode.Layout;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.yutaroapp.camemode.Activity.AddActivity;
import com.example.yutaroapp.camemode.R;
import com.example.yutaroapp.camemode.Utility;

import static android.app.Activity.RESULT_OK;


public class AddLayout extends RelativeLayout {

    private AddActivity mAddActivity;

    public int freeDayArrayCount = 7;

    // レイアウト定義用
    public RadioGroup categoryRole;
    public EditText displayName;
    public EditText password;
    public RadioGroup categorySns;
    public EditText snsUserName;
    public CheckBox[] freeDay = new CheckBox[freeDayArrayCount];
    public RadioGroup whichCharge;
    public Spinner spinnerRegion;
    public Spinner spinnerSex;
    public Spinner spinnerAge;
    public EditText imaginationHope;
    public FloatingActionButton fab; // 登録FABボタン
    /* HELPボタン */
    public ImageView CategoryRoleHelp;
    public ImageView DisplayNameHelp;
    public ImageView SnsUserNameHelp;
    public ImageView ImaginationHopeHelp;

    /**
     * コンテキスト.
     */
    private Context mContext;

    /* コンストラクタ（理由もわからずとりあえず3つ設けている） */
    public AddLayout(Context context) {
        super(context);
        mContext = context;
        mAddActivity = new AddActivity();
    }

    public AddLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mAddActivity = new AddActivity();
    }

    public AddLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mAddActivity = new AddActivity();
    }

    /**
     * レイアウトの初期化
     * @param view
     */
    public void setUpViews(View view) {
        categoryRole = (RadioGroup) view.findViewById(R.id.category_role);
        displayName = (EditText) view.findViewById(R.id.display_name);
        password = (EditText) view.findViewById(R.id.password);
        categorySns = (RadioGroup) view.findViewById(R.id.category_sns);
        snsUserName = (EditText) view.findViewById(R.id.sns_user_name);

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
        imaginationHope = (EditText) view.findViewById(R.id.imagination_hope);

        CategoryRoleHelp = (ImageView) view.findViewById(R.id.category_role_help);
        DisplayNameHelp = (ImageView) view.findViewById(R.id.display_name_help);
        SnsUserNameHelp = (ImageView) view.findViewById(R.id.sns_user_name_help);
        ImaginationHopeHelp = (ImageView) view.findViewById(R.id.imagination_hope_help);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CategoryRoleHelp.setTooltipText("あなたがやりたい「種別」を\n選択してください。");
            DisplayNameHelp.setTooltipText("当アプリ内での表示名です。");
            SnsUserNameHelp.setTooltipText("上記項目で選択した\nSNSのアカウント名[ID]を\nご入力ください。");
            ImaginationHopeHelp.setTooltipText("撮影のイメージを\n170文字までご入力ください。");
        }

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }
}