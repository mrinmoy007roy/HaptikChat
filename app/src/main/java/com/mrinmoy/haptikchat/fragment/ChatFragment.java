package com.mrinmoy.haptikchat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrinmoy.haptikchat.R;
import com.mrinmoy.haptikchat.adapter.ChatAdapter;
import com.mrinmoy.haptikchat.database.ChatDataBaseHandler;
import com.mrinmoy.haptikchat.model.Chat;
import com.mrinmoy.haptikchat.model.ChatResponse;
import com.mrinmoy.haptikchat.model.FavAndCount;
import com.mrinmoy.haptikchat.rest.Client;
import com.mrinmoy.haptikchat.rest.ClientInterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends android.support.v4.app.Fragment {

    Context mContext;
    Chat[] chats;
    int totalChats = 0;
    ChatAdapter chatAdapter;
    ChatDataBaseHandler db;
    private RecyclerView mReCylerView;

    public ChatFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.chat_fragment, container, false);
        initializeVariables(view);
        return view;
    }

    private void initializeVariables(View rootView) {
        mReCylerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewChat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mReCylerView.setLayoutManager(linearLayoutManager);
        chatAdapter = new ChatAdapter(chats, mContext, (ChatAdapter.ChatItemListener) getActivity());
        mReCylerView.setAdapter(chatAdapter);
        db = new ChatDataBaseHandler(mContext);
        ClientInterface clientInterface = Client.getClient().create(ClientInterface.class);
        Call<ChatResponse> call = clientInterface.getChatDetails();
        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                totalChats = response.body().getCount();
                if (totalChats > 0) {
                    chats = new Chat[totalChats];
                    chats = response.body().getChatmodels();

                    Map<String, Integer> countHashMap = new HashMap<>();
                    int count = 0;
                    for (int i = 0; i < totalChats; i++) {
                        if (countHashMap.containsKey(chats[i].getUserName())) {
                            count = 1 + countHashMap.get(chats[i].getUserName());
                        } else {
                            count = 1;
                        }
                        countHashMap.put(chats[i].getUserName(), count);
                        db.updateFavAndCount(new FavAndCount(chats[i].getUserName(), chats[i].getPublicName(), 0, countHashMap.get(chats[i].getUserName()),chats[i].getImgPath()));
                    }


                    chatAdapter.setData(chats, mContext, (ChatAdapter.ChatItemListener) getActivity());
                    chatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
            }
        });
    }

}
