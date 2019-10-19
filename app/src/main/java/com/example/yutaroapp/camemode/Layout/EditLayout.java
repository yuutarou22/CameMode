package com.example.yutaroapp.camemode.Layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yutaroapp.camemode.Activity.AddActivity;
import com.example.yutaroapp.camemode.R;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class EditLayout extends RelativeLayout {

    public int freeDayArrayCount = 7;

    // レイアウト定義用
    public ImageView backButton;
    public ImageView deleteButton;
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
    public TextView imaginationTextCount;
    public Button updateButton; // 更新ボタン
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
    public EditLayout(Context context) {
        super(context);
        mContext = context;
    }

    public EditLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public EditLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    /**
     * レイアウトの初期化
     *
     * @param view
     */
    public void setUpViews(View view) {
        backButton = (ImageView) view.findViewById(R.id.back_button);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)mContext).finish();
            }
        });
        deleteButton = (ImageView) view.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDo: 削除処理
            }
        });
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
        imaginationTextCount = (TextView) view.findViewById(R.id.imagination_text_count);

        CategoryRoleHelp = (ImageView) view.findViewById(R.id.category_role_help);
        DisplayNameHelp = (ImageView) view.findViewById(R.id.display_name_help);
        SnsUserNameHelp = (ImageView) view.findViewById(R.id.sns_user_name_help);
        ImaginationHopeHelp = (ImageView) view.findViewById(R.id.imagination_hope_help);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CategoryRoleHelp.setTooltipText(getResources().getString(R.string.tooltip_category));
            DisplayNameHelp.setTooltipText(getResources().getString(R.string.tooltip_display_name));
            SnsUserNameHelp.setTooltipText(getResources().getString(R.string.tooltip_sns_user_name));
            ImaginationHopeHelp.setTooltipText(getResources().getString(R.string.tooltip_imagination));
        }

        updateButton = (Button) view.findViewById(R.id.update_button);

        imaginationHope.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {
                int textColor = Color.GRAY;
                int textLength = str.length();
                imaginationTextCount.setText(Integer.toString(textLength) + "/200");
                if (textLength >= 200) {
                    textColor = Color.RED;
                }
                imaginationTextCount.setTextColor(textColor);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void createTutorial(final AddActivity activity) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ShowcaseConfig config = new ShowcaseConfig();
                config.setDelay(100);
                config.setFadeDuration(200);

                MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(activity, "R.id.category_role_help");
                sequence.setConfig(config);

                sequence.addSequenceItem(
                        new MaterialShowcaseView.Builder(activity)
                                .setTarget(CategoryRoleHelp)
                                .setContentText(R.string.add_tutorial_01)
                                .setDismissText(R.string.tutorial_ok_text)
                                .setDismissOnTouch(true)
                                .build()
                );

                sequence.start();
            }
        });
    }
}