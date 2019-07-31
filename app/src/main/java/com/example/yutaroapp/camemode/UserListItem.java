package com.example.yutaroapp.camemode;

import android.graphics.Bitmap;

public class UserListItem {
  private Bitmap mUserIcon = null;
  private String mUserName = null;
  private String mCategoryRole = null;

  /**
   * コンストラクタ
   * @param mUserIcon アイコン
   * @param mUserName 表示名
   * @param mCategoryRole カテゴリー種別
   */
  public UserListItem(Bitmap mUserIcon, String mUserName, String mCategoryRole){
    this.mUserIcon = mUserIcon;
    this.mUserName = mUserName;
    this.mCategoryRole = mCategoryRole;
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
  public String getCategoryRole() {
    return mCategoryRole;
  }
}
