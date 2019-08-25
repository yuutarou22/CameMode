package com.example.yutaroapp.camemode;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<UserListItem> list;
    private View.OnClickListener listener;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.mUserIcon.setImageBitmap(list.get(position).getUserIcon());
        viewHolder.mUserName.setText(list.get(position).getUserName());
        viewHolder.mCategoryRole.setText(list.get(position).getCategoryRole());
        viewHolder.mImaginationHope.setText(list.get(position).getImaginationHope());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                // Fragment 生成するか？
                Log.d("onBindViewHolder", "onClick!!! getUserName:" + list.get(position).getUserName()); // リスナー検知は出来ている
            }
        });

        if (list.get(position).getCategoryRole().equals("カメラマン")) {
            viewHolder.mUserIcon.setImageResource(R.drawable.cameraman);
            viewHolder.mUserIcon.setBackgroundColor(Color.argb(120, 0, 153, 204));
        } else if (list.get(position).getCategoryRole().equals("モデル")) {
            viewHolder.mUserIcon.setImageResource(R.drawable.model);
            viewHolder.mUserIcon.setBackgroundColor(Color.argb(120, 255, 0, 0));
        } else {
            viewHolder.mUserIcon.setImageResource(R.drawable.camera_and_model);
            viewHolder.mUserIcon.setBackgroundColor(Color.argb(120, 255, 241, 0));
        }
    }

    public void setOnClickItemListener(View.OnClickListener listener) {
        this.listener = listener;
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
