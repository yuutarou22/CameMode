package com.example.yutaroapp.camemode.Layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.yutaroapp.camemode.Activity.AddActivity;
import com.example.yutaroapp.camemode.Activity.MainActivity;
import com.example.yutaroapp.camemode.Activity.SearchActivity;
import com.example.yutaroapp.camemode.R;

import uk.co.deanwild.materialshowcaseview.IShowcaseListener;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

import static com.example.yutaroapp.camemode.Activity.MainActivity.startActivityforResult;


public class MainLayout extends RelativeLayout {

    /**
     * コンテキスト.
     */
    private Context mContext;

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
                new MaterialShowcaseView.Builder(activity)
                        .setTarget(addButton)
                        .setContentText("ここから追加することができます。")
                        .setDismissText("了解です！")
                        .singleUse("R.id.add_content_button")
                        .setListener(new IShowcaseListener() {
                            @Override
                            public void onShowcaseDisplayed(MaterialShowcaseView showcaseView) {

                            }

                            @Override
                            public void onShowcaseDismissed(MaterialShowcaseView showcaseView) {

                            }
                        })
                        .show();

                new MaterialShowcaseView.Builder(activity)
                        .setTarget(searchButton)
                        .setContentText("ここから検索することができます。")
                        .setDismissText("OKOK！")
                        .singleUse("R.id.search_content_button")
                        .setListener(new IShowcaseListener() {
                            @Override
                            public void onShowcaseDisplayed(MaterialShowcaseView showcaseView) {

                            }

                            @Override
                            public void onShowcaseDismissed(MaterialShowcaseView showcaseView) {

                            }
                        })
                        .show();
            }
        });
    }
}