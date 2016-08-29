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
import com.mrinmoy.haptikchat.adapter.FavAdapter;
import com.mrinmoy.haptikchat.database.ChatDataBaseHandler;
import com.mrinmoy.haptikchat.model.FavAndCount;

import java.util.List;

public class FavFragment extends android.support.v4.app.Fragment {
    ChatDataBaseHandler db;
    private RecyclerView recyclerViewFav;
    private Context mContext;
    private List<FavAndCount> favAndCounts;
    private FavAdapter favAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fav_fragment, container, false);
        recyclerViewFav = (RecyclerView) view.findViewById(R.id.recyclerViewFav);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerViewFav.setLayoutManager(linearLayoutManager);
        db = new ChatDataBaseHandler(mContext);
        favAdapter = new FavAdapter(favAndCounts, mContext);
        recyclerViewFav.setAdapter(favAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    public void load() {
        if (mContext == null)
            return;
        if (db == null)
            return;
        favAndCounts = db.getAllFavAndCounts();
        if (favAndCounts != null && favAndCounts.size() > 0) {
            favAdapter.setData(favAndCounts, mContext);
            favAdapter.notifyDataSetChanged();
        }
    }
}
