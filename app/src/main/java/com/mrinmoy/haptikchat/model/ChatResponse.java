package com.mrinmoy.haptikchat.model;

import com.google.gson.annotations.SerializedName;


public class ChatResponse {

    @SerializedName("count")
    private int count;
    @SerializedName("messages")

    private Chat[] chatmodels;

    public ChatResponse(Chat[] chatmodels, int count) {
        this.count = count;
        if (count > 0) {
            this.chatmodels = new Chat[count];
            this.chatmodels = chatmodels;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Chat[] getChatmodels() {
        return chatmodels;
    }

    public void setChatmodels(Chat[] chatmodels) {
        this.chatmodels = chatmodels;
    }


}
