package com.mrinmoy.haptikchat.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrinmoy.haptikchat.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {
    public TextView name, msg, msgTime;
    public ImageView profileImg;

    public ChatViewHolder(View v) {
        super(v);
        profileImg = (ImageView) v.findViewById(R.id.img);
        name = (TextView) v.findViewById(R.id.name);
        msg = (TextView) v.findViewById(R.id.msg);
        msgTime = (TextView) v.findViewById(R.id.msgTime);
    }
}