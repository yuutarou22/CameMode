package com.example.yutaroapp.camemode;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<UserListItem> list;

    /**
     * コンストラクタ
     * @param list MainActivity #display() からの userListItems
     */
    public UserRecyclerViewAdapter (List<UserListItem> list) {
        this.list = list;
    }

    /**
     * ViewHolderの生成
     *
     * @param viewGroup
     * @param i
     * @return viewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_info_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    /**
     * 指定された位置（position）のコンテンツを更新する
     * @param viewHolder
     * @param position
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.mUserIcon.setImageBitmap(list.get(position).getUserIcon());
        viewHolder.mUserName.setText(list.get(position).getUserName());
        viewHolder.mCategoryRole.setText(list.get(position).getCategoryRole());

        if (viewHolder.mCategoryRole.getText().equals("カメラマン")) {
            viewHolder.mCategoryRole.setBackgroundColor(Color.argb(120, 0, 0, 255));
        } else if (list.get(position).getCategoryRole().equals("モデル")) {
            viewHolder.mCategoryRole.setBackgroundColor(Color.argb(120, 255, 0, 0));
        } else {
            viewHolder.mCategoryRole.setBackgroundColor(Color.argb(120, 100, 100, 100));
        }
    }

    /**
     * UserListItemのリストサイズを返す
     *
     * @return list.size() リストサイズ
     */
    @Override
    public int getItemCount() {
        return list.size();
    }
}
