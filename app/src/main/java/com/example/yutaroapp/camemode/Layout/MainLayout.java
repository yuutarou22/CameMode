package com.example.yutaroapp.camemode.Layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.example.yutaroapp.camemode.Activity.AddActivity;
import com.example.yutaroapp.camemode.Activity.MainActivity;
import com.example.yutaroapp.camemode.Activity.SearchActivity;
import com.example.yutaroapp.camemode.R;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

import static com.example.yutaroapp.camemode.Activity.MainActivity.startActivityforResult;


public class MainLayout extends RelativeLayout {

    /**
     * コンテキスト.
     */
    private Context mContext;

    Toolbar toolbar;
    ImageView appLogoImage;
    ImageView addButton;
    ImageView searchButton;

    /* コンストラクタ（理由もわからずとりあえず3つ設けている） */
    public MainLayout(Context context) {
        super(context);
        mContext = context;
    }

    public MainLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public MainLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    /**
     * レイアウトの初期化
     *
     * @param view
     */
    public void setUpViews(View view) {
        appLogoImage = (ImageView) view.findViewById(R.id.application_logo);

        addButton = (ImageView) view.findViewById(R.id.add_content_button);
        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddActivity.class);
                startActivityforResult((Activity) mContext, intent);
            }
        });

        searchButton = (ImageView) view.findViewById(R.id.search_content_button);
        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivityforResult((Activity) mContext, intent);
            }
        });
    }

    public void createTutorial(final MainActivity activity) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ShowcaseConfig config = new ShowcaseConfig();
                config.setDelay(100);
                config.setFadeDuration(200);
                MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(activity, "R.id.application_logo");

                sequence.setConfig(config);

                sequence.addSequenceItem(
                        new MaterialShowcaseView.Builder(activity)
                                .setTarget(appLogoImage)
                                .setContentText(R.string.main_tutorial_01)
                                .setDismissText(R.string.tutorial_ok_text)
                                .setShapePadding(-150)
                                .build()
                );

                sequence.addSequenceItem(
                        new MaterialShowcaseView.Builder(activity)
                                .setTarget(addButton)
                                .setContentText(R.string.main_tutorial_02)
                                .setDismissText(R.string.tutorial_ok_text)
                                .setShapePadding(-100)
                                .build()
                );

                sequence.addSequenceItem(
                        new MaterialShowcaseView.Builder(activity)
                                .setTarget(searchButton)
                                .setContentText(R.string.main_tutorial_03)
                                .setDismissText(R.string.tutorial_ok_text)
                                .setShapePadding(-100)
                                .build()
                );

                sequence.start();
            }
        });
    }
}