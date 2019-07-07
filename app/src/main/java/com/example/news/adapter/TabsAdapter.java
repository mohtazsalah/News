package com.example.news.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.news.ui.fragment.AllNews;
import com.example.news.ui.fragment.Profile;

public class TabsAdapter extends FragmentPagerAdapter {

    AllNews allNews = new AllNews();
    Profile pro = new Profile();

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return allNews;
            case 1:
                return pro;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "news";
            case 1:
                return "profile";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
