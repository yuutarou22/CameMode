package com.example.yutaroapp.camemode;

import android.graphics.Bitmap;

public class UserListItem {
    private Bitmap mUserIcon = null;
    private String mUserName = null;
    private String mSnsUserName = null;
    private String mCategoryRole = null;
    private String mImaginationHope = null;
    private int mAge = 0;
    private int mSex = 0;

    /**
     * コンストラクタ
     *
     * @param mUserName     表示名
     * @param mCategoryRole カテゴリー種別
     */
    public UserListItem(String mUserName, String mSnsUserName, String mCategoryRole, String mImaginationHope, int mAge, int mSex) {
        this.mUserName = mUserName;
        this.mSnsUserName = mSnsUserName;
        this.mCategoryRole = mCategoryRole;
        this.mImaginationHope = mImaginationHope;
        this.mAge = mAge;
        this.mSex = mSex;
    }

    // setter
    public void setUserIcon(Bitmap mUserIcon) {
        this.mUserIcon = mUserIcon;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public void setSnsUserName(String mSnsUserName) {
        this.mSnsUserName = mSnsUserName;
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

    public String getSnsUserName() {
        return mSnsUserName;
    }

    public String getCategoryRole() {
        return mCategoryRole;
    }

    public String getImaginationHope() {
        return mImaginationHope;
    }

    public int getAge() {
        return mAge;
    }

    public int getSex() {
        return mSex;
    }
}
