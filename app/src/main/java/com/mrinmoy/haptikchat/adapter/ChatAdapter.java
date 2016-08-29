package com.mrinmoy.haptikchat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mrinmoy.haptikchat.R;
import com.mrinmoy.haptikchat.model.Chat;
import com.mrinmoy.haptikchat.viewHolder.ChatViewHolder;


public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    private Chat[] chats;
    private Context mContext;
    private ChatItemListener chatItemListener;

    public ChatAdapter(Chat[] chats, Context context, ChatItemListener activity) {
        if (null != chats && chats.length > 1) {
            this.chats = new Chat[chats.length];
            this.chats = chats;
        }
        this.mContext = context;
        this.chatItemListener = activity;
    }

    public void setData(Chat[] chats, Context context, ChatItemListener activity) {
        if (null != chats && chats.length > 1) {
            this.chats = new Chat[chats.length];
            this.chats = chats;
        }
        this.mContext = context;
        this.chatItemListener = activity;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);

        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, final int position) {
        Glide.with(mContext).load(chats[position].getImgPath())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.drawable.profile)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.profileImg);
        holder.name.setText(chats[position].getPublicName());
        holder.msg.setText(chats[position].getMessage());

        String string = chats[position].getMsgTime();
        String[] parts = string.split("T");
        String date = parts[0];
        String time = parts[1];
        String[] more = time.split(":");
        holder.msgTime.setText(more[0] + ":" + more[1]);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                chatItemListener.onChatItemLongClicked(chats[position]);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        if (null == chats || chats.length < 1)
            return 0;

        return chats.length;
    }

    public interface ChatItemListener {
        void onChatItemLongClicked(Chat chat);
    }

}
