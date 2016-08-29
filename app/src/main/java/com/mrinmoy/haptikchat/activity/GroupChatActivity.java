package com.mrinmoy.haptikchat.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mrinmoy.haptikchat.R;
import com.mrinmoy.haptikchat.adapter.ChatAdapter;
import com.mrinmoy.haptikchat.adapter.SectionsPagerAdapter;
import com.mrinmoy.haptikchat.database.ChatDataBaseHandler;
import com.mrinmoy.haptikchat.fragment.FavFragment;
import com.mrinmoy.haptikchat.model.Chat;
import com.mrinmoy.haptikchat.model.FavAndCount;

public class GroupChatActivity extends AppCompatActivity implements ChatAdapter.ChatItemListener {

    MenuItem mFav;
    ChatDataBaseHandler db;
    FragmentManager fragmentManager;
    FavFragment favFragment;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private Chat chat;

    private static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        db = new ChatDataBaseHandler(this);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_group_chat, menu);
        return true;
    }

    public Fragment getActiveFragment(int position) {
        String name = makeFragmentName(mViewPager.getId(), position);
        return fragmentManager.findFragmentByTag(name);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mFav = menu.findItem(R.id.action_favourite);
        mFav.setVisible(false);
        mFav.setChecked(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_favourite) {
            if (chat != null) {

                FavAndCount fav = db.getFavAndCount(chat.getUserName());

                boolean isChecked = !mFav.isChecked();
                item.setChecked(isChecked);
                db.updateFav(fav, fav.getFavCount() + 1);

                favFragment = (FavFragment) getActiveFragment(1);
                favFragment.load();

                Snackbar snackbar = Snackbar.make(mViewPager, chat.getPublicName()+"'s message is added to Favourites", Snackbar.LENGTH_SHORT);
                snackbar.show();
                mFav.setVisible(false);
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onChatItemLongClicked(Chat chat) {
        this.chat = chat;
        mFav.setVisible(true);
    }

}
