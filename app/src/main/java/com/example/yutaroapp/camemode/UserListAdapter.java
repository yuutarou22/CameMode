package com.example.yutaroapp.camemode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<UserListItem> {

  private int mResource;
  private List<UserListItem> mItems;
  private LayoutInflater mInflater;

  public UserListAdapter(@NonNull Context context, int resource, @NonNull List<UserListItem> items) {
    super(context, resource, items);
    mResource = resource;
    mItems = items;
    mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @SuppressLint("ResourceAsColor")
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view;

    if (convertView != null) {
      view = convertView;
    } else {
      view = mInflater.inflate(mResource, null);
    }

    // リストに表示する要素取得
    UserListItem item = mItems.get(position);

    // アイコン画像を設定
    ImageView userIcon = (ImageView)view.findViewById(R.id.user_icon);
    userIcon.setImageBitmap(item.getUserIcon());

    // ユーザ名を設定
    TextView userName = (TextView)view.findViewById(R.id.user_name);
    userName.setText(item.getUserName());

    return view;
  }
}
