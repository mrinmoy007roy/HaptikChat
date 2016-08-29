package com.mrinmoy.haptikchat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mrinmoy.haptikchat.fragment.ChatFragment;
import com.mrinmoy.haptikchat.fragment.FavFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public static final int CHAT_FRAGMENT_POSITION = 0;
    public static final int FAV_FRAGMENT_POSITION = 1;
    private ChatFragment chatFragment;
    private FavFragment favFragment;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case CHAT_FRAGMENT_POSITION: {
                chatFragment = new ChatFragment();
                return chatFragment;
            }

            case FAV_FRAGMENT_POSITION: {
                favFragment = new FavFragment();
                return favFragment;
            }
        }
        return null;

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "GROUP CHAT";
            case 1:
                return "DETAILS";
        }
        return null;
    }

}
