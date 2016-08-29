package com.mrinmoy.haptikchat.model;

import com.google.gson.annotations.SerializedName;


public class Chat {
    /*

    body: "How is everyone?",
username: "ryan-a",
Name: "Ryan",
image-url: "http://haptikdev.s3.amazonaws.com/content/06138bc5af6023646ede0e1f7c1eac75d64dd2f45a516f8746413832a94f75f3.jpeg",
message-time: "2016-01-01T05:32:34"

     */

    @SerializedName("body")
    private String message;
    @SerializedName("username")
    private String userName;
    @SerializedName("Name")
    private String publicName;
    @SerializedName("image-url")
    private String imgPath;
    @SerializedName("message-time")
    private String msgTime;

    public Chat(String message, String userName, String publicName, String imgPath, String msgTime) {
        this.message = message;
        this.userName = userName;
        this.publicName = publicName;
        this.imgPath = imgPath;
        this.msgTime = msgTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }
}
