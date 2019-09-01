package com.example.yutaroapp.camemode.Layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.yutaroapp.camemode.Activity.SearchActivity;
import com.example.yutaroapp.camemode.R;


public class SearchResultLayout extends RelativeLayout {
    // レイアウト定義用
    Button researchButton;

    /**
     * コンテキスト.
     */
    private Context mContext;

    /* コンストラクタ（理由もわからずとりあえず3つ設けている） */
    public SearchResultLayout(Context context) {
        super(context);
        mContext = context;
    }

    public SearchResultLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public SearchResultLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    /**
     * レイアウトの初期化
     *
     * @param view
     */
    public void setUpViews(View view) {
        researchButton = (Button) view.findViewById(R.id.reseach_button);
        researchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                ((Activity) mContext).finish();
                mContext.startActivity(intent);
            }
        });
        // ToDo: リスト表示
//          displayListView;
    }
}