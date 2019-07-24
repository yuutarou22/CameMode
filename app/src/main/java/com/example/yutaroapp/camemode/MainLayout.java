package com.example.yutaroapp.camemode;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static com.example.yutaroapp.camemode.MainActivity.*;

public class MainLayout extends RelativeLayout {

    /** コンテキスト. */
    private Context mContext;
    private Activity mMainActivity;

    /* コンストラクタ（理由もわからずとりあえず3つ設けている） */
    public MainLayout(Context context) {
        super(context);
        mContext = context;
//        mMainActivity = mContext.getApplicationContext()
    }
    public MainLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }
    public MainLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setUpViews(View view) {
        Button addButton = (Button)view.findViewById(R.id.add_content_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddActivity.class);
                startActivityforResult((Activity) mContext.getApplicationContext(), intent);
            }
        });

        Button searchButton = (Button)view.findViewById(R.id.search_content_button);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SearchActivity.class);
//                startActivityforResult(MainActivity.startActivityforResult();, intent);
//                MainActivity.startActivityforResult(MainActivity, intent);
            }
        });
    }
}
