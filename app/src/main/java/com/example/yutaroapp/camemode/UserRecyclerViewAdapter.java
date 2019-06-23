package com.example.yutaroapp.camemode;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<UserListItem> list;

    public UserRecyclerViewAdapter (List<UserListItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_info_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.mUserIcon.setImageBitmap(list.get(position).getUserIcon());
        viewHolder.mUserName.setText(list.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
