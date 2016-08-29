package com.mrinmoy.haptikchat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mrinmoy.haptikchat.R;
import com.mrinmoy.haptikchat.model.FavAndCount;

import java.util.List;
import java.util.Locale;


public class FavAdapter extends SectionedRecyclerViewAdapter<FavAdapter.MainVH> {

    private List<FavAndCount> favAndCounts;
    private Context mContext;

    public FavAdapter(List<FavAndCount> favAndCounts, Context mContext) {
        this.favAndCounts = favAndCounts;
        this.mContext = mContext;
    }

    public void setData(List<FavAndCount> favAndCounts, Context mContext) {
        this.favAndCounts = favAndCounts;
        this.mContext = mContext;
    }

    @Override
    public int getSectionCount() {
        return 2; // number of sections.
    }

    @Override
    public int getItemCount(int section) {
        if (null == favAndCounts || favAndCounts.size() < 1)
            return 0;
        return favAndCounts.size(); // number of items in section (section index is parameter).
    }

    @Override
    public void onBindHeaderViewHolder(MainVH holder, int section) {
        // Setup header view.
        if (section == 0) {
            holder.header.setText("Favourites");
        } else if (section == 1) {
            holder.header.setText("Total Messages");
        }

    }

    @Override
    public void onBindViewHolder(MainVH holder, int section, int relativePosition, int absolutePosition) {



        holder.name.setText(favAndCounts.get(relativePosition).getPublicName());
        if (absolutePosition < favAndCounts.size()) {
            holder.count.setText(String.format(Locale.getDefault(), "%d", favAndCounts.get(relativePosition).getFavCount()));
                Glide.with(mContext).load(favAndCounts.get(relativePosition).getImg())
                        .thumbnail(0.5f)
                        .crossFade()
                        .placeholder(R.drawable.profile)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imageView);
        } else if (absolutePosition >= favAndCounts.size()) {
            holder.count.setText(String.format(Locale.getDefault(), "%d", favAndCounts.get(relativePosition).getMsgCount()));

                Glide.with(mContext).load(favAndCounts.get(relativePosition).getImg())
                        .thumbnail(0.5f)
                        .crossFade()
                        .placeholder(R.drawable.profile)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imageView);
        }


    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(viewType == VIEW_TYPE_HEADER ? R.layout.header_item : R.layout.fav_item, parent, false);
        return new MainVH(v);
    }

    public static class MainVH extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView header, name, count;

        public MainVH(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgView);
            header = (TextView) itemView.findViewById(R.id.header);
            name = (TextView) itemView.findViewById(R.id.name);
            count = (TextView) itemView.findViewById(R.id.count);

        }
    }
}