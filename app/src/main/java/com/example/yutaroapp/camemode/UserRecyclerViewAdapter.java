package com.example.yutaroapp.camemode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.nifcloud.mbaas.core.NCMBObject;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<UserListItem> list;
    private View.OnClickListener listener;
    private FragmentManager fragmentManager;
    private Context mContext;

    /**
     * コンストラクタ
     *
     * @param list MainActivity #display() からの userListItems
     */
    public UserRecyclerViewAdapter(List<UserListItem> list, FragmentManager fragmentManager, Context context) {
        this.list = list;
        this.fragmentManager = fragmentManager;
        this.mContext = context;
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
     *
     * @param viewHolder
     * @param position
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        int color = 0;
        String age = null;
        String sex = null;

        viewHolder.mUserName.setText(list.get(position).getUserName());
        viewHolder.mCategoryRole.setText(list.get(position).getCategoryRole());
        viewHolder.mImaginationHope.setText(list.get(position).getImaginationHope());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                Log.d("onBindViewHolder", "onClick!!! getUserName:" + list.get(position).getUserName());
                createFragment(position);
            }
        });

        if (list.get(position).getCategoryRole().equals("カメラマン")) {
            viewHolder.mUserIcon.setImageResource(R.drawable.cameraman);
            color = viewHolder.mLineView.getContext().getResources().getColor(R.color.colorCardViewBackgroundCameraman);
        } else if (list.get(position).getCategoryRole().equals("モデル")) {
            viewHolder.mUserIcon.setImageResource(R.drawable.model);
            color = viewHolder.mLineView.getContext().getResources().getColor(R.color.colorCardViewBackgroundModel);
        } else {
            viewHolder.mUserIcon.setImageResource(R.drawable.camera_and_model);
            color = viewHolder.mLineView.getContext().getResources().getColor(R.color.colorCardViewBackgroundBoth);
        }
        viewHolder.mLineView.setBackgroundColor(color);

        ImageButton snsTransitionButton = (ImageButton) viewHolder.mSnsTranslationButton;
        snsTransitionButton.setImageDrawable(setSNSImageButton(list.get(position).getCategorySns()));
        snsTransitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ToDo: インスタの分岐を追加する
                Utility.snsTranslationActivity(list.get(position).getSnsUserName(), list.get(position).getCategorySns(), mContext);
            }
        });

        if (list.get(position).getAge() == 0) {
            age = "未選択";
        } else {
            age = String.valueOf(list.get(position).getAge() * 10) + "代";
        }
        viewHolder.mAge.setText(age);

        if (list.get(position).getSex() == 0) {
            sex = "未選択";
        } else if (list.get(position).getSex() == 1) {
            sex = "男性";
        } else {
            sex = "女性";
        }
        viewHolder.mSex.setText(sex);
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

    private void createFragment(int position) {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.BOTTOM);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment listItemFragment = new ListItemFragment();
        listItemFragment.setEnterTransition(slide);

        Bundle bundle = setUserInfoToBundle(position);
        listItemFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, listItemFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private Bundle setUserInfoToBundle(int position) {
        NCMBObject SelectedUserInfo = Utility.userInfoDataList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("DisplayName", SelectedUserInfo.getString("DisplayName"));
        bundle.putString("CategoryRole", SelectedUserInfo.getString("CategoryRole"));
        bundle.putString("CategorySNS", SelectedUserInfo.getString("CategorySNS"));
        List<Boolean> list = SelectedUserInfo.getList("FreeDay");
        boolean[] array = new boolean[list.size()];
        for (int i = 0; i<list.size(); i++) {
            array[i] = list.get(i).booleanValue();
        }
        bundle.putBooleanArray("FreeDay", array);
        bundle.putString("ImaginationHope", SelectedUserInfo.getString("ImaginationHope"));
        bundle.putString("SNSUserName", SelectedUserInfo.getString("SNSUserName"));
        bundle.putInt("SpinnerAgeInt", SelectedUserInfo.getInt("SpinnerAgeInt"));
        bundle.putInt("SpinnerRegionInt", SelectedUserInfo.getInt("SpinnerRegionInt"));
        bundle.putInt("SpinnerSex", SelectedUserInfo.getInt("SpinnerSex"));
        bundle.putString("WhichCharge", SelectedUserInfo.getString("WhichCharge"));
        return bundle;
    }

    /*  各SNSへ遷移するボタンの画像 */
    private Drawable setSNSImageButton(String categorySns) {
        Drawable drawable;
        if (categorySns.equals("Twitter")) {
            drawable = ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.image_twitter_button, null);
        } else {
            drawable = ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.image_instagram_button, null);
        }
        return drawable;
    }
}
