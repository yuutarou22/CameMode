package com.example.yutaroapp.camemode;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public View mLineView = null;
    public ImageView mUserIcon = null;
    public TextView mUserName = null;
    public Button mSnsTranslationButton = null;
    public TextView mCategoryRole = null;
    public TextView mImaginationHope = null;
    public TextView mAge = null;
    public TextView mSex = null;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mLineView = (View) itemView.findViewById(R.id.line_view);
        mUserIcon = (ImageView) itemView.findViewById(R.id.user_icon);
        mUserName = (TextView) itemView.findViewById(R.id.user_name);
        mSnsTranslationButton = (Button) itemView.findViewById(R.id.sns_transition);
        mCategoryRole = (TextView) itemView.findViewById(R.id.category_role);
        mImaginationHope = (TextView) itemView.findViewById(R.id.imagination_hope);
        mAge = (TextView) itemView.findViewById(R.id.age);
        mSex = (TextView) itemView.findViewById(R.id.sex);
    }
}
