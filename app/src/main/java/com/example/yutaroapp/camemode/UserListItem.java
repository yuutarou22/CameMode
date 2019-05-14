package com.example.yutaroapp.camemode;

import android.graphics.Bitmap;

public class UserListItem {
  private Bitmap mUserIcon = null;
  private String mUserName = null;

  // constructor
  public UserListItem(){}
  public UserListItem(Bitmap mUserIcon, String mUserName){
    this.mUserIcon = mUserIcon;
    this.mUserName = mUserName;
  }

  // setter
  public void setUserIcon(Bitmap mUserIcon) {
    this.mUserIcon = mUserIcon;
  }
  public void setUserName(String mUserName) {
    this.mUserName = mUserName;
  }

  // getter
  public Bitmap getUserIcon() {
    return mUserIcon;
  }
  public String getUserName() {
    return mUserName;
  }
}
