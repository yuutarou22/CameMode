package com.example.yutaroapp.camemode;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView mUserIcon = null;
    public TextView mUserName = null;
    public TextView mCategoryRole = null;
    public TextView mImaginationHope = null;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mUserIcon = (ImageView) itemView.findViewById(R.id.user_icon);
        mUserName = (TextView) itemView.findViewById(R.id.user_name);
        mCategoryRole = (TextView) itemView.findViewById(R.id.category_role);
        mImaginationHope = (TextView) itemView.findViewById(R.id.imagination_hope);
    }
}
