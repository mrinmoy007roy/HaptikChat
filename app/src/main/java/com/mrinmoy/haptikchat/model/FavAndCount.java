package com.mrinmoy.haptikchat.model;

import com.google.gson.annotations.SerializedName;


public class FavAndCount {
    @SerializedName("username")
    private String userName;
    @SerializedName("Name")
    private String publicName;
    @SerializedName("favCount")
    private int favCount;
    @SerializedName("msgCount")
    private int msgCount;
    @SerializedName("img-url")
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public FavAndCount() {
        super();
    }

    public FavAndCount(String userName, String publicName, int favCount, int msgCount,String img) {
        this.userName = userName;
        this.publicName = publicName;
        this.favCount = favCount;
        this.msgCount = msgCount;
        this.img= img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public int getFavCount() {
        return favCount;
    }

    public void setFavCount(int favCount) {
        this.favCount = favCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

}
