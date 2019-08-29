package com.example.yutaroapp.camemode;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yutaroapp.camemode.Activity.AddActivity;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<UserListItem> list;
    private View.OnClickListener listener;
    private FragmentManager fragmentManager;

    /**
     * コンストラクタ
     * @param list MainActivity #display() からの userListItems
     */
    public UserRecyclerViewAdapter (List<UserListItem> list, FragmentManager fragmentManager) {
        this.list = list;
        this.fragmentManager = fragmentManager;
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
                Log.d("onBindViewHolder", "onClick!!! getUserName:" + list.get(position).getUserName()); // リスナー検知は出来ている
//                Log.d("onBindViewHolder","Utility.userInfoDataList DisplayName: " + Utility.userInfoDataList.get(position).getString("DisplayName"));

                // リスト項目を選択するとFragment生成する。
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new ListItemFragment());
                    fragmentTransaction.commit();
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
