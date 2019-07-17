package com.example.yutaroapp.camemode;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static com.example.yutaroapp.camemode.MainActivity.*;

public class MainLayout extends RelativeLayout {

//    FloatingActionButton fabMenuButton = (FloatingActionButton) findViewById(R.id.fab);
//    FloatingActionButton fabAddButton = (FloatingActionButton) findViewById(R.id.fab_add);
//    FloatingActionButton fabSearchButton = (FloatingActionButton) findViewById(R.id.fab_search);

    /** コンテキスト. */
    private Context mContext;
    LinearLayout mFabSearchLayout;
    LinearLayout mFabAddLayout;
    FloatingActionButton mFab;

    /* コンストラクタ（理由もわからずとりあえず3つ設けている） */
    public MainLayout(Context context) {
        super(context);
        mContext = context;
        mFabSearchLayout = new LinearLayout(mContext);
        mFabAddLayout = new LinearLayout(mContext);
        /* 問題箇所 */
//        mFab = new FloatingActionButton(mContext);
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
        // FAB処理
        FloatingActionButton fabMenuButton = (FloatingActionButton) view.findViewById(R.id.fab);
        fabMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int iconWhile = 350;
                if (mFABState == mFABState.CLOSE) {
                    fabOpen(iconWhile);
                } else {
                    fabClose();
                }
            }
        });

        FloatingActionButton fabAddButton = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fabAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClose(); // FABを閉じてから遷移
                Intent intent = new Intent(mContext, AddActivity.class);
//                mContext.startActivity(intent);
                MainActivity.startActivityforResult((Activity) mContext.getApplicationContext(), intent);
            }
        });

        FloatingActionButton fabSearchButton = (FloatingActionButton) view.findViewById(R.id.fab_search);
        fabSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClose(); // FABを閉じてから遷移
                Intent intent = new Intent(mContext, SearchActivity.class);
//                mContext.startActivity(intent);
                MainActivity.startActivityforResult((Activity) mContext.getApplicationContext(), intent);
            }
        });
    }

    private void fabOpen(int iconWhile) {

        View mFabBackground = findViewById(R.id.fab_background);

        mFabSearchLayout.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mFabSearchLayout, "translationY", -iconWhile);
        animator.setDuration(250);
        animator.start();

        mFabAddLayout.setVisibility(View.VISIBLE);
        animator = ObjectAnimator.ofFloat(mFabAddLayout, "translationY", -iconWhile * 2);
        animator.setDuration(250);
        animator.start();

        /* 問題箇所 */
        mFab = new FloatingActionButton(mContext);

        mFab.setImageResource(R.drawable.ic_clear_button);
        animator = ObjectAnimator.ofFloat(mFab, "rotation", 270);
        animator.setDuration(250);
        animator.start();

        mFABState = MainActivity.FABState.OPEN;
        mFabBackground.animate().alpha(0.8f).setDuration(300);
    }

    private void fabClose() {
        LinearLayout mFabSearchLayout = findViewById(R.id.fab_search_layout);
        LinearLayout mFabAddLayout = findViewById(R.id.fab_add_layout);
        FloatingActionButton mFab = findViewById(R.id.fab);
        View mFabBackground = findViewById(R.id.fab_background);

        ObjectAnimator animator = ObjectAnimator.ofFloat(mFabSearchLayout, "translationY", 0);
        animator.setDuration(200);
        animator.start();
        mFabSearchLayout.setVisibility(View.GONE);

        animator = ObjectAnimator.ofFloat(mFabAddLayout, "translationY", 0);
        animator.setDuration(200);
        animator.start();
        mFabAddLayout.setVisibility(View.GONE);

        mFab.setImageResource(R.drawable.ic_menu_button);
        animator = ObjectAnimator.ofFloat(mFab, "rotation", 0);
        animator.setDuration(200);
        animator.start();

        mFABState = MainActivity.FABState.CLOSE;
        mFabBackground.animate().alpha(0f).setDuration(200);
    }
}
