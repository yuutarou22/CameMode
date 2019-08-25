package com.example.yutaroapp.camemode;

import android.graphics.Bitmap;

public class UserListItem {
  private Bitmap mUserIcon = null;
  private String mUserName = null;
  private String mCategoryRole = null;
  private String mImaginationHope = null;

  /**
   * コンストラクタ
   * @param mUserName 表示名
   * @param mCategoryRole カテゴリー種別
   */
  public UserListItem(String mUserName, String mCategoryRole, String mImaginationHope){
    this.mUserName = mUserName;
    this.mCategoryRole = mCategoryRole;
    this.mImaginationHope = mImaginationHope;
  }

  // setter
  public void setUserIcon(Bitmap mUserIcon) {
    this.mUserIcon = mUserIcon;
  }
  public void setUserName(String mUserName) {
    this.mUserName = mUserName;
  }
  public void setImaginationHope(String mImaginationHope) {
      this.mImaginationHope = mImaginationHope;
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
  public String getImaginationHope() {
      return mImaginationHope;
  }
}
